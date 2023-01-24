package com.kiatnakinbank.naos.flowmanagementservice.bizunit;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.dto.decision.DecisionDto;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMDecisionEntity;
import com.kiatnakinbank.naos.flowmanagementservice.repository.TbMDecisionRepository;

@Service
public class DecisionUnit {

    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionUnit.class);

    @Autowired
    private TbMDecisionRepository tbMDecisionRepository;

    public List<DecisionDto> getDecisionListByCondition(String decisionName) {
        LOGGER.info("============ DecisionUnit getDecisionListByCondition ============");
        return this.mapTbMDecisionListToDecisionDto(
                (decisionName == null || decisionName.isEmpty()) ? tbMDecisionRepository.findAll()
                        : tbMDecisionRepository.findByDecisionNameContainingIgnoreCase(decisionName));
    }

    private List<DecisionDto> mapTbMDecisionListToDecisionDto(List<TbMDecisionEntity> list) {
        List<DecisionDto> resultList = new ArrayList<DecisionDto>();
        for (TbMDecisionEntity row : list) {
            DecisionDto decisionEntity = new DecisionDto();
            decisionEntity.setDecisionCode(row.getDecisionCode());
            decisionEntity.setDecisionName(row.getDecisionName());
            decisionEntity.setDecisionResult(row.getDecisionResult());
            decisionEntity.setIsActive(row.getIsActive());
            decisionEntity.setCreateDate(row.getCreateDate());
            decisionEntity.setCreateUser(row.getCreateUser());
            decisionEntity.setUpdateDate(row.getUpdateDate());
            decisionEntity.setUpdateUser(row.getUpdateUser());
            resultList.add(decisionEntity);
        }
        return resultList;
    }

    public String getDecisionResultByDecisionCode(String decisionCode) {
        return this.tbMDecisionRepository.findDecisionResultByDecisionCode(decisionCode).get(0);
    }

    public List<String> getDecisionCodeAll() {
        return this.tbMDecisionRepository.findDecisionCodeAll();
    }

    public boolean checkDuplicateDecision(String decisionCode, String decisionName) {
        return ((this.tbMDecisionRepository.countByDecisionCode(decisionCode) > 0)
                || (this.tbMDecisionRepository.countByDecisionName(decisionName) > 0));
    }

    public void saveDecision(TbMDecisionEntity tbMDecisionEntity) {
        tbMDecisionRepository.save(tbMDecisionEntity);
    }

    public TbMDecisionEntity getTbMDecisionByDecisionCode(String decisionCode) {
        LOGGER.info("============ DecisionUnit getTbMDecisionByDecisionCode ============");
        return this.tbMDecisionRepository.findByDecisionCode(decisionCode).get(0);
    }

    public boolean checkFlowInactive(@NotNull String decisionCode) {
        return this.tbMDecisionRepository.findByDecisionCode(decisionCode).get(0).getIsActive().equals(ActiveFlag.N);
    }

    public void deleteDecisionByDecisionCode(String decisionCode) {
        this.tbMDecisionRepository.deleteById(decisionCode);
    }

    
    public boolean checkDecisionCodeIsNotNull(String decisionCode) {
        return (this.tbMDecisionRepository.countByDecisionCode(decisionCode) > 0);
    }

}
