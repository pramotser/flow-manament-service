package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.DecisionUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.dto.decision.DecisionDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.decision.ReqCreateDecision;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMDecisionEntity;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@Service
public class DecisionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionService.class);
    @Autowired
    private DecisionUnit decisionUnit;

    public List<DecisionDto> getDecisionListByCondition(String decisionName) {
        LOGGER.info("============ DecisionService getUniversalListByCondition ============");
        return this.decisionUnit.getDecisionListByCondition(decisionName);
    }

    public ResponseEntity<Response> getNewDecisionCode() {
        LOGGER.info("============ DecisionService getNewDecisionCode ============");
        List<String> decisionStringList = this.decisionUnit.getDecisionCodeAll();
        List<Long> decisionLongList = new ArrayList<>();
        for (String row : decisionStringList) {
            decisionLongList.add(new Long(row.substring(2, row.length())));
        }
        Long max = Collections.max(decisionLongList);
        String newDecisionCode = Constants.PreFixCode.DECISION + String.format("%06d", ++max);
        return Util.createResponse(Constants.ResponseCode.OK, "Success.", newDecisionCode);
    }

    public ResponseEntity<Response> createDecision(ReqCreateDecision requestBody) {
        LOGGER.info("============ DecisionService createDecision ============");
        LOGGER.info("requestBody : "+requestBody);

        if (this.decisionUnit.checkDuplicateDecision(requestBody.getDecisionCode(), requestBody.getDecisionName())) {
            return Util.createResponse(Constants.ResponseCode.CONFLICT, "FLow Id is Duplicate.",
                    new ArrayList<>());
        }
        TbMDecisionEntity tbMDecisionEntity = mapTbmDecision(requestBody);
        tbMDecisionEntity.setCreateAttribute("SYSTEM");
        tbMDecisionEntity.setIsActive(requestBody.getIsActive());
        this.decisionUnit.saveDecision(tbMDecisionEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Create Decision Success", new ArrayList<>());
    }

    public ResponseEntity<Response> updateDecision(ReqCreateDecision requestBody) {
        LOGGER.info("============ DecisionService updateDecision ============");
        if (!this.decisionUnit.checkDuplicateDecision(requestBody.getDecisionCode(), requestBody.getDecisionName())) {
            return Util.createResponse(Constants.ResponseCode.CONFLICT, "Decision Code is Data Not Found.",
                    new ArrayList<>());
        }
        TbMDecisionEntity tbMDecisionEntity =  this.decisionUnit.getTbMDecisionByDecisionCode(requestBody.getDecisionCode());
        tbMDecisionEntity.setDecisionName(requestBody.getDecisionName());
        tbMDecisionEntity.setDecisionResult(requestBody.getDecisionResult());
        tbMDecisionEntity.setUpdateAttribute("SYSTEM");
        tbMDecisionEntity.setIsActive(requestBody.getIsActive());
        this.decisionUnit.saveDecision(tbMDecisionEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Update Decision Success", new ArrayList<>());
    }

    private TbMDecisionEntity mapTbmDecision(ReqCreateDecision requestBody) {
        return new TbMDecisionEntity(requestBody.getDecisionCode(),requestBody.getDecisionName(),requestBody.getDecisionResult());
    }

    public ResponseEntity<Response> deleteDecision(ReqCreateDecision requestBody) {
        LOGGER.info("============ DecisionService deleteFlow ============");
        if (!this.decisionUnit.checkDuplicateDecision(requestBody.getDecisionCode(), requestBody.getDecisionName())) {
            return Util.createResponse(Constants.ResponseCode.CONFLICT, "Decision Code is Data Not Found.",
                    new ArrayList<>());
        }
        if (!this.decisionUnit.checkFlowInactive(requestBody.getDecisionCode())) {
            return Util.createResponse(Constants.ResponseCode.ACCEPTED,
                    "Cannot delete because the status Decision is active.",
                    new ArrayList<>());
        }
        this.decisionUnit.deleteDecisionByDecisionCode(requestBody.getDecisionCode());
        return Util.createResponse(Constants.ResponseCode.OK, "Delete Decision Success.", new ArrayList<>());
    }
}
