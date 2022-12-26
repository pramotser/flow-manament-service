package com.kiatnakinbank.naos.flowmanagementservice.service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.DropdownUnit;
import com.kiatnakinbank.naos.flowmanagementservice.bizunit.FlowUnit;
import com.kiatnakinbank.naos.flowmanagementservice.dto.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DropdownService {
    @Autowired
    private DropdownUnit dropdownUnit;

    public List<DropdownResponse> getFLowList() {
        return dropdownUnit.getFlowList();
    }
    public List<DropdownResponse> getResultParamList() {
        return dropdownUnit.getResultParamList();
    }
}
