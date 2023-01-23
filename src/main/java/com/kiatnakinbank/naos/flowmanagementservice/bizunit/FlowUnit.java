package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.FlowListDto;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowNewEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowNewRepository;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMFlowRepository;

@Service
public class FlowUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowUnit.class);

    @Autowired
    private TbMFlowRepository tbMFlowRepository;

    @Autowired
    private TbMFlowNewRepository tbMFlowNewRepository;

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

    public String getResultParamCodeByFLowId(String flowId) {
        return tbMFlowRepository.findResultParamByFlowId(Long.valueOf(flowId)).get(0);
    }

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
        LOGGER.info("============ FlowUnit getFlowListByFlowId ============");
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
            flowDto.setIsActive(row.getIsActive());
            flowDto.setCreateDate(row.getCreateDate());
            flowDto.setCreateUser(row.getCreateUser());
            flowDto.setUpdateDate(row.getUpdateDate());
            flowDto.setUpdateUser(row.getUpdateUser());
            flowDtoList.add(flowDto);
        }
        return flowDtoList;
    }
}
