package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.DecisionUnit;
import com.kiatnakinbank.naos.flowmanagementservice.bizunit.UniversalUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.UniversalFieldDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.dto.universalField.RequestCreateUniversalField;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMUniversalFieldNewEntity;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@Service
public class UniversalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalService.class);

    @Autowired
    private UniversalUnit universalUnit;

    @Autowired
    private DecisionUnit decisionUnit;

    public List<UniversalFieldDto> getUniversalListByCondition(String universalName) {
        LOGGER.info("============ UniversalService getUniversalListByCondition ============");
        return this.universalUnit.getUniversalListByCondition(universalName);
    }

    public ResponseEntity<Response> createUniversalField(RequestCreateUniversalField requestBody) {
        LOGGER.info("============ UniversalService createUniversalField ============");
        if (this.universalUnit.checkDuplicateUniversalCode(requestBody.getUniversalCode())) {
            return Util.createResponse(Constants.ResponseCode.CONFLICT, "Universal Code is Duplicate.",
                    new ArrayList<>());
        }
        TbMUniversalFieldNewEntity tbMResultParamEntity = mapTbMUniversalField(requestBody);
        tbMResultParamEntity.setCreateAttribute("SYSTEM");
        tbMResultParamEntity.setIsActive(requestBody.getIsActive());
        this.universalUnit.saveTbMUniversalFieldEntity(tbMResultParamEntity);

        return Util.createResponse(Constants.ResponseCode.OK, "Create Universal Field Success", new ArrayList<>());
    }

    private TbMUniversalFieldNewEntity mapTbMUniversalField(RequestCreateUniversalField requestBody) {
        return new TbMUniversalFieldNewEntity(requestBody.getUniversalCode(), requestBody.getUniversalName(),
                requestBody.getUniversalType(), requestBody.getFieldType());
    }

    public ResponseEntity<Response> updateUniversalField(RequestCreateUniversalField requestBody) {
        LOGGER.info("============ UniversalService updateUniversalField ============");
        if (!this.universalUnit.checkDuplicateUniversalCode(requestBody.getUniversalCode())) {
            return Util.createResponse(Constants.ResponseCode.NOT_MODIFIED, "Universal Code is Data Not Found.",
                    new ArrayList<>());
        }
        TbMUniversalFieldNewEntity tbMUniversalFieldNewEntity = this.universalUnit
                .getTbMUniversalFieldById(requestBody.getUniversalCode());
        if (this.universalUnit.checkUniversalUsing(requestBody.getUniversalCode())) {
            if (!tbMUniversalFieldNewEntity.getIsActive().equals(requestBody.getIsActive())) {
                return Util.createResponse(Constants.ResponseCode.ACCEPTED,
                        "Result Param is currently in use, cannot be changed to Inactive.", new ArrayList<>());
            }
            // if
            // (!requestBody.getResultParamType().equalsIgnoreCase(tbMResultParamEntity.getResultParamType())){
            // return Util.createResponse(Constants.ResponseCode.ACCEPTED,
            // "Result Param is currently in use, cannot be changed to Result Param Type.",
            // new ArrayList<>());
            // }
        }
        tbMUniversalFieldNewEntity.setUniversalName(requestBody.getUniversalName());
        tbMUniversalFieldNewEntity.setUniversalType(requestBody.getUniversalType());
        tbMUniversalFieldNewEntity.setFieldType(requestBody.getFieldType());
        tbMUniversalFieldNewEntity.setIsActive(requestBody.getIsActive());
        tbMUniversalFieldNewEntity.setUpdateAttribute("SYSTEM");
        this.universalUnit.saveTbMUniversalFieldEntity(tbMUniversalFieldNewEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Update Universal Field Success", new ArrayList<>());
    }

    public ResponseEntity<Response> deleteUniversalField(String universalCode) {
        LOGGER.info("============ UniversalService deleteUniversalField ============");
        LOGGER.info("requestBody :" + universalCode);
        if (!this.universalUnit.checkDuplicateUniversalCode(universalCode)) {
            return Util.createResponse(Constants.ResponseCode.NOT_MODIFIED, "Universal Code is Data Not Found.",
                    new ArrayList<>());
        }

        if (!this.universalUnit.checkDuplicateUniversalFieldInactive(universalCode)) {
            return Util.createResponse(Constants.ResponseCode.ACCEPTED, "Universal Field must be inactive.",
                    new ArrayList<>());
        }
        this.universalUnit.deleteUniversalFieldByUniversalCode(universalCode);
        return Util.createResponse(Constants.ResponseCode.OK, "Delete Result Param Success.", new ArrayList<>());
    }

    public ResponseEntity<Response> getDecisionResultByDecisionCode(String decisionCode) {
        LOGGER.info("============ UniversalService getDecisionResultByDecisionCode ============");
        String decisionResult = this.decisionUnit.getDecisionResultByDecisionCode(decisionCode);
        return Util.createResponse(Constants.ResponseCode.OK, "Success", this
                .mapTbMUniversalFieldToDropdownResponse(this.universalUnit.getTbMUniversalFieldById(decisionResult)));
    }

    private DropdownResponse mapTbMUniversalFieldToDropdownResponse(TbMUniversalFieldNewEntity tbMUniversalField) {
        return new DropdownResponse(tbMUniversalField.getUniversalCode(),
                tbMUniversalField.getUniversalCode(), tbMUniversalField);
    }
}
