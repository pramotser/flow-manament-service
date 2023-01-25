package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.common.util.CommonUtils;
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
        return this.tbMFlowNewRepository.countByFlowCode(flowCode) > 0;
    }

    public TbMFlowNewEntity getTbMFlowNewByFlowCode(String flowCode) {
        return this.tbMFlowNewRepository.findByFlowCode(flowCode).get(0);
    }

    public boolean checkFlowCodeActiveByFlowCode(String flowCode) {
        return this.tbMFlowNewRepository.findByFlowCodeAndIsActive(flowCode, ActiveFlag.Y).size() > 0;
    }

    public void deleteFlowByFlowCode(String flowCode) {
        this.tbMFlowNewRepository.deleteById(flowCode);
    }

    public boolean checkGraphFlowIsNotNull(String flowCode) {
        List<TbMFlowNewEntity> tbMEntity = this.tbMFlowNewRepository.findByFlowCode(flowCode);
        if (!CommonUtils.isNotNullAndEmpty(tbMEntity)) {
            return false;
        }
        return CommonUtils.isNotBlank(tbMEntity.get(0).getFlowJson());
    }

    public boolean checkEffectDate(String flowCode, String decisionCode) {
        Boolean flagEffectDate = true;
        List<TbMFlowNewEntity> flowAllWithDecisionCode = this.tbMFlowNewRepository.findByFlowDecisionCode(decisionCode);
        TbMFlowNewEntity tbMFlowNewEntity = this.tbMFlowNewRepository.findByFlowCode(flowCode).get(0);
        for (TbMFlowNewEntity row : flowAllWithDecisionCode) {
            // LOGGER.info("IsActive : " + row.getIsActive());
            // LOGGER.info("getFlowExpirationDate : " + row.getFlowExpirationDate());
            if (ActiveFlag.Y.equals(row.getIsActive()) && row.getFlowExpirationDate() == null) {
                LOGGER.info("FLOW CODE : " + row.getFlowCode());
                LOGGER.info("FLOW_EFFECTIVE_DATE : " + row.getFlowEffectiveDate());
                LOGGER.info(" EFFECTIVE_DATE want Active : " + tbMFlowNewEntity.getFlowEffectiveDate());
                int result = tbMFlowNewEntity.getFlowEffectiveDate().compareTo(row.getFlowEffectiveDate());
                LOGGER.info(" result row FLOW_EFFECTIVE_DATE : " + result);
                result = tbMFlowNewEntity.getFlowEffectiveDate().compareTo(new Date());
                LOGGER.info(" result Current date : " + result);
                if (tbMFlowNewEntity.getFlowEffectiveDate().compareTo(row.getFlowEffectiveDate()) <= 0
                        || tbMFlowNewEntity.getFlowEffectiveDate().compareTo(new Date()) <= 0) {
                    flagEffectDate = false;
                    break;
                }
            }
        }
        LOGGER.info(" flagEffectDate : " + flagEffectDate);
        return flagEffectDate;
    }

    public TbMFlowNewEntity getTbMFlowNewLastActiveByDecisionCode(String flowDecisionCode) {
        List<TbMFlowNewEntity> flowAllWithDecisionCode = this.tbMFlowNewRepository
                .findByFlowDecisionCode(flowDecisionCode);
        TbMFlowNewEntity tbMFlowNewEntity = new TbMFlowNewEntity();
        for (TbMFlowNewEntity row : flowAllWithDecisionCode) {
            if (ActiveFlag.Y.equals(row.getIsActive()) && row.getFlowExpirationDate() == null) {
                tbMFlowNewEntity = row;
            }
        }
        return tbMFlowNewEntity;
    }

    public boolean checkFlowNameDuplicate(String flowName, String flowDecisionCode) {
        return this.tbMFlowNewRepository.countByFlowNameAndFlowDecisionCode(flowName, flowDecisionCode) > 0;
    }

}
