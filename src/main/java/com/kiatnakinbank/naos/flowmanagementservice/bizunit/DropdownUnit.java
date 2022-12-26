package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import com.kiatnakinbank.naos.flowmanagementservice.dto.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbmFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DropdownUnit {
    @Autowired
    private TbMFlowRepository tbMFlowRepository;

    public List<DropdownResponse> getFlowList() {
        return this.mapTbMFlowToDropdownResponse(tbMFlowRepository.findAll());
    }

    public List<DropdownResponse> getResultParamList() {
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (String row : this.tbMFlowRepository.findDistinctFlowResultParam()) {
            dropdownResponses.add(new DropdownResponse(row, row, row));
        }
        return dropdownResponses;
    }

    private List<DropdownResponse> mapTbMFlowToDropdownResponse(List<TbmFlowEntity> tbmFlowEntityList) {
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (TbmFlowEntity row : tbmFlowEntityList) {
            dropdownResponses.add(new DropdownResponse(row.getFlowName(), row.getFlowName(), row));
        }
        return dropdownResponses;
    }
}
