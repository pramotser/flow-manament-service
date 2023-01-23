package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.FlowListDto;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowNewEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowNewRepository;

@Service
public class FlowUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowUnit.class);

    @Autowired
    private TbMFlowNewRepository tbMFlowNewRepository;

    public String generateFlowCodeByDecisionCode(String flowDecisionCode) {
        List<String> flowCodeList = tbMFlowNewRepository.findByDecisionCode(flowDecisionCode);
        String newFlowCode = "";
        if (flowCodeList != null && flowCodeList.size() > 0) {
            List<Long> flowCodeLongList = new ArrayList<>();
            for (String row : flowCodeList) {
                flowCodeLongList.add(new Long(row.substring(10, row.length())));
            }
            Long max = Collections.max(flowCodeLongList);
            newFlowCode = flowDecisionCode + Constants.PreFixCode.FLOW + String.format("%06d", ++max);
        } else {
            newFlowCode = flowDecisionCode + Constants.PreFixCode.FLOW + String.format("%06d", 1);
        }
        return newFlowCode;
    }
    public TbMFlowNewEntity saveFlow(TbMFlowNewEntity tbmFlowEntity) {
        return tbMFlowNewRepository.save(tbmFlowEntity);
    }

    public List<FlowListDto> getFlowListByDecisionCode(String flowDecisionCode) {
        List<TbMFlowNewEntity> tbMFlowNewEntitList = tbMFlowNewRepository.findByFlowDecisionCode(flowDecisionCode);
        return this.mapTbMFlowNewEntityToFlowListDto(tbMFlowNewEntitList);
    }

    private List<FlowListDto> mapTbMFlowNewEntityToFlowListDto(List<TbMFlowNewEntity> tbMFlowNewEntitList) {
        List<FlowListDto> flowDtoList = new ArrayList<>();
        for (TbMFlowNewEntity row : tbMFlowNewEntitList) {
            FlowListDto flowDto = new FlowListDto();
            flowDto.setFlowCode(row.getFlowCode());
            flowDto.setFlowName(row.getFlowName());
            flowDto.setFlowDecisionCode(row.getFlowDecisionCode());
            flowDto.setFlowEffectiveDate(row.getFlowEffectiveDate());
            flowDto.setFlowExpirationDate(row.getFlowExpirationDate());
            flowDto.setFlowJson(row.getFlowJson());
            flowDto.setIsActive(row.getIsActive());
            flowDto.setCreateDate(row.getCreateDate());
            flowDto.setCreateUser(row.getCreateUser());
            flowDto.setUpdateDate(row.getUpdateDate());
            flowDto.setUpdateUser(row.getUpdateUser());
            flowDtoList.add(flowDto);
        }
        return flowDtoList;
    }

    public boolean checkFlowCodeIsNotNull(String flowCode) {
        return this.tbMFlowNewRepository.countByFlowCode(flowCode) > 0 ;
    }

    public TbMFlowNewEntity getTbMFlowNewByFlowCode(String flowCode) {
        return this.tbMFlowNewRepository.findByFlowCode(flowCode).get(0);
    }
}
