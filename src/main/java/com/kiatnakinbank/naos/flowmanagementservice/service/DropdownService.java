package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.DropdownUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.properties.DropdownProperties;

@Service
public class DropdownService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownService.class);
    @Autowired
    private DropdownUnit dropdownUnit;
    @Autowired
    private DropdownProperties dropdownTypeProperties;

    public List<DropdownResponse> getDropdownByType(String dropdownType, boolean showCode) {
        LOGGER.info("============ DropdownService getDropdownByType ============");
        List<DropdownResponse> dropdownList = new ArrayList<DropdownResponse>();
        switch (dropdownType) {
            case Constants.DropdownType.FLOW_LIST:
                dropdownList = dropdownUnit.getFlowList(showCode);
                break;
            case Constants.DropdownType.RESULT_PARAM_LIST:
                dropdownList = dropdownUnit.getResultParamList(showCode);
                break;
            default:
                break;
        }
        return dropdownList;
    }

    public Boolean validateHeaderDropdown(HttpServletRequest request) {
        LOGGER.info("============ DropdownService validateHeaderDropdown ============");
        if (request.getHeader(Constants.HeaderKey.DROPDOWN_TYPE) == null
                || request.getHeader(Constants.HeaderKey.DROPDOWN_TYPE).isEmpty()) {
            return false;
        }
        if (dropdownTypeProperties.getTypeList().get(request.getHeader(Constants.HeaderKey.DROPDOWN_TYPE)) == null) {
            return false;
        }
        return true;
    }
}
