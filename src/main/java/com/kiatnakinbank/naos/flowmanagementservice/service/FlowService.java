package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.FlowUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateFlow;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbmFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@Service
public class FlowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowService.class);
    @Autowired
    private FlowUnit flowUnit;

    public List<FlowDto> getFlowListByCondition(String flowName) {
        LOGGER.info("============ FlowService getFlowListByCondition ============");
        return this.flowUnit.getFlowListByCondition(flowName);
    }

    public ResponseEntity<Response> createFlow(RequestCreateFlow requestCreateFlow) {
        LOGGER.info("============ FlowService createFlow ============");
        if (flowUnit.checkDuplicateFlow(requestCreateFlow.getFlowId())) {
            return Util.createResponseByCode(Constants.ResponseCode.CONFLICT, new ArrayList<>());
        }
        TbmFlowEntity tbmFlowEntity = mapTbMFlow(requestCreateFlow);
        tbmFlowEntity.setCreateAttribute("SYSTEM");
        tbmFlowEntity.setIsActive(requestCreateFlow.getIsActive());
        return Util.createResponseByCode(Constants.ResponseCode.OK,
                mapTbmFlowToFlowDto(flowUnit.saveFlow(tbmFlowEntity)));
    }

    public ResponseEntity<Response> updateFlow(RequestCreateFlow requestCreateFlow) {
        LOGGER.info("============ FlowService updateFlow ============");
        if (!flowUnit.checkDuplicateFlow(requestCreateFlow.getFlowId())) {
            return Util.createResponseByCode(Constants.ResponseCode.NOT_MODIFIED, new ArrayList<>());
        }
        TbmFlowEntity tbmFlowEntity = flowUnit.getTbmFlowByFlowId(requestCreateFlow.getFlowId());
        tbmFlowEntity.setFlowName(requestCreateFlow.getFlowName());
        tbmFlowEntity.setFlowResultParam(requestCreateFlow.getResultParam());
        tbmFlowEntity.setDecisionFlow(requestCreateFlow.getDecisionFlow());
        tbmFlowEntity.setIsActive(requestCreateFlow.getIsActive());
        tbmFlowEntity.setUpdateAttribute("SYSTEM");
        return Util.createResponseByCode(Constants.ResponseCode.OK,
                mapTbmFlowToFlowDto(flowUnit.saveFlow(tbmFlowEntity)));
    }

    public ResponseEntity<Response> deleteFlow(RequestCreateFlow requestCreateFlow) {
        LOGGER.info("============ FlowService deleteFlow ============");
        if (!flowUnit.checkFlowInactive(requestCreateFlow.getFlowId())) {
            return Util.createResponseByCode(Constants.ResponseCode.ACCEPTED, new ArrayList<>());
        }
        flowUnit.deleteFlow(requestCreateFlow.getFlowId());
        return Util.createResponseByCode(Constants.ResponseCode.OK, new ArrayList<>());
    }

    private FlowDto mapTbmFlowToFlowDto(TbmFlowEntity tbmFlowEntity) {
        return new FlowDto(tbmFlowEntity.getFlowId(), tbmFlowEntity.getFlowName(), tbmFlowEntity.getFlowResultParam(),
                tbmFlowEntity.getStartFlowId(), tbmFlowEntity.getDecisionFlow(), tbmFlowEntity.getIsActive(),
                tbmFlowEntity.getCreateDate(), tbmFlowEntity.getCreateUser(), tbmFlowEntity.getUpdateDate(),
                tbmFlowEntity.getUpdateUser());
    }

    public TbmFlowEntity mapTbMFlow(RequestCreateFlow requestCreateFlow) {
        return new TbmFlowEntity(requestCreateFlow.getFlowId(), requestCreateFlow.getFlowName(),
                requestCreateFlow.getResultParam(), null, requestCreateFlow.getDecisionFlow());
    }
}
