package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.DropdownUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownRequest;
import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownResponse;

@Service
public class DropdownService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownService.class);
    @Autowired
    private DropdownUnit dropdownUnit;

    public List<DropdownResponse> getDropdownListByType(DropdownRequest requestBody) {
        LOGGER.info("============ DropdownService getDropdownListByType ============");
        List<DropdownResponse> dropdownList = new ArrayList<DropdownResponse>();
        switch (requestBody.getDropdownType()) {
            case Constants.DropdownType.FLOW_LIST:
                dropdownList = dropdownUnit.getFlowList(requestBody.getFlagShowCode());
                break;
            case Constants.DropdownType.RESULT_PARAM_LIST:
                dropdownList = dropdownUnit.getResultParamList(requestBody.getFlagShowCode());
                break;
            case Constants.DropdownType.UNIVERSAL_FIELD_LIST:
                dropdownList = dropdownUnit.getUniversalFieldList(requestBody.getFlagShowCode(),Constants.UniversalType.UNIVERSAL);
                break;
            case Constants.DropdownType.RESULT_DECISION_LIST:
                dropdownList = dropdownUnit.getUniversalFieldList(requestBody.getFlagShowCode(),Constants.UniversalType.RESULT_DECISION);
                break;
            default:
                break;
        }
        return dropdownList;
    }

    public Boolean validateDropdownType(DropdownRequest requestBody) {
        LOGGER.info("============ DropdownService validateHeaderDropdown ============");
        if (requestBody.getDropdownType() == null || requestBody.getDropdownType().isEmpty()) {
            return false;
        }
        if (!Arrays.asList(Constants.DropdownType.RESULT_DECISION_LIST, Constants.DropdownType.UNIVERSAL_FIELD_LIST)
                .contains(requestBody.getDropdownType())) {
            return false;
        }
        return true;
    }
}
