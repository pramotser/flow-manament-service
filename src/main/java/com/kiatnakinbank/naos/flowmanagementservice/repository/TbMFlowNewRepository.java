package com.kiatnakinbank.naos.flowmanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowNewEntity;

public interface TbMFlowNewRepository extends JpaRepository<TbMFlowNewEntity, String> {
    @Query(value = " SELECT FLOW_CODE as flowCode " +
            " FROM TB_M_FLOW_NEW WHERE FLOW_DECISION_CODE= :flowDecisionCode", nativeQuery = true)
    List<String> findByDecisionCode(String flowDecisionCode);

    List<TbMFlowNewEntity> findByFlowDecisionCode(String flowDecisionCode);
}
