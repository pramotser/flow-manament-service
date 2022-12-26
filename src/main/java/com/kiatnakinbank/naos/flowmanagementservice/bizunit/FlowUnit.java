package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbmFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowUnit.class);

    @Autowired
    private TbMFlowRepository tbMFlowRepository;

    public List<FlowDto> getFlowListByCondition(String flowName) {
        List<TbmFlowEntity> tbmFlowEntityList = new ArrayList<>();
        if (flowName == null && flowName.length() == 0) {
            tbmFlowEntityList = tbMFlowRepository.findAll();
        } else {
            tbmFlowEntityList = tbMFlowRepository.findByFlowNameContainingIgnoreCase(flowName);
        }
        return this.mapTbMFlowToFlowDto(tbmFlowEntityList);
    }

    private List<FlowDto> mapTbMFlowToFlowDto(List<TbmFlowEntity> tbmFlowEntityList) {
        List<FlowDto> flowDtoList = new ArrayList<>();
        for (TbmFlowEntity row : tbmFlowEntityList) {
            FlowDto flowDto = new FlowDto();
            flowDto.setFlowId(row.getFlowId());
            flowDto.setFlowName(row.getFlowName());
            flowDto.setResultParam(row.getFlowResultParam());
            flowDto.setStartFlowId(row.getStartFlowId());
            flowDto.setDecisionFlow(row.getDecisionFlow());
            flowDto.setIsActive(row.getIsActive());
            flowDtoList.add(flowDto);
        }
        return flowDtoList;
    }

    public Boolean checkDuplicateFlow (long flowId){
        return tbMFlowRepository.countByFlowId(flowId) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public TbmFlowEntity saveFlow(TbmFlowEntity tbmFlowEntity) {
       return tbMFlowRepository.save(tbmFlowEntity);
    }

    public TbmFlowEntity getTbmFlowByFlowId (Long flowId){
        return tbMFlowRepository.findByFlowId(flowId).get(0);
    }
}
