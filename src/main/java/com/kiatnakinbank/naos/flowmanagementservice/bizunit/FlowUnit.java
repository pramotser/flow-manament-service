package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowEntity;
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

    public List<FlowDto> getFlowListByFlowId(String flowId) {
        LOGGER.info("============ FlowUnit getFlowListByFlowId ============");
        List<TbMFlowEntity> tbmFlowEntityList = new ArrayList<>();
        if (flowId == null || flowId.isEmpty()) {
            tbmFlowEntityList = tbMFlowRepository.findAll();
        } else {
            tbmFlowEntityList = tbMFlowRepository.findByFlowId(Long.valueOf(flowId));
        }
        return this.mapTbMFlowToFlowDto(tbmFlowEntityList);
    }

    private List<FlowDto> mapTbMFlowToFlowDto(List<TbMFlowEntity> tbmFlowEntityList) {
        List<FlowDto> flowDtoList = new ArrayList<>();
        for (TbMFlowEntity row : tbmFlowEntityList) {
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

    public Boolean checkDuplicateFlow(long flowId) {
        return tbMFlowRepository.countByFlowId(flowId) > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

    public TbMFlowEntity saveFlow(TbMFlowEntity tbmFlowEntity) {
        return tbMFlowRepository.save(tbmFlowEntity);
    }

    public TbMFlowEntity getTbmFlowByFlowId(Long flowId) {
        return tbMFlowRepository.findByFlowId(flowId).get(0);
    }

    public Boolean checkFlowInactive(long flowId) {
        return tbMFlowRepository.findByFlowId(flowId).get(0).getIsActive().equals(ActiveFlag.N);
    }

    public void deleteFlow(long flowId) {
        tbMFlowRepository.deleteById(flowId);
    }
}
