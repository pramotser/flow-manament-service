package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.dto.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMResultParamEntity;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbmFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMResultParamRepository;

@Service
public class DropdownUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownUnit.class);
    @Autowired
    private TbMFlowRepository tbMFlowRepository;

    @Autowired
    private TbMResultParamRepository tbMResultParamRepository;

    public List<DropdownResponse> getFlowList(boolean showCode) {
        LOGGER.info("============ DropdownUnit getFlowList ============");
        return this.mapTbMFlowToDropdownResponse(tbMFlowRepository.findAll(), showCode);
    }

    public List<DropdownResponse> getResultParamList(boolean showCode) {
        LOGGER.info("============ DropdownUnit getResultParamList ============");
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (TbMResultParamEntity row : this.tbMResultParamRepository.findAll()) {
            dropdownResponses.add(new DropdownResponse(row.getResultParamCode(),
                    (showCode) ? row.getResultParamCode() + " : " + row.getResultParamName() : row.getResultParamName(),
                    row));
        }
        return dropdownResponses;
    }

    private List<DropdownResponse> mapTbMFlowToDropdownResponse(List<TbmFlowEntity> tbmFlowEntityList,
            boolean showCode) {
        List<DropdownResponse> dropdownResponses = new ArrayList<>();
        for (TbmFlowEntity row : tbmFlowEntityList) {
            dropdownResponses.add(new DropdownResponse(row.getFlowName(),
                    (showCode) ? row.getFlowId() + " : " + row.getFlowName() : row.getFlowName(), row));
        }
        return dropdownResponses;
    }
}
