package com.kiatnakinbank.naos.flowmanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMDecisionEntity;

public interface TbMDecisionRepository extends JpaRepository<TbMDecisionEntity, String> {

    long countByDecisionResult(String decisionResult);

    @Query(value = "SELECT DECISION_RESULT FROM TB_M_DECISION_NEW WHERE DECISION_CODE = :decisionCode ", nativeQuery = true)
    List<String> findDecisionResultByDecisionCode(String decisionCode);

    List<TbMDecisionEntity> findByDecisionNameContainingIgnoreCase(String decisionName);

    @Query(value = "SELECT DECISION_CODE FROM TB_M_DECISION_NEW", nativeQuery = true)
    List<String> findDecisionCodeAll();


    long countByDecisionCode(String decisionCode);

    long countByDecisionName(String decisionName);

    List<TbMDecisionEntity> findByDecisionCode(String decisionCode);

    @Query(value = "SELECT * FROM TB_M_DECISION_NEW ORDER BY DECISION_CODE ASC", nativeQuery = true)
    List<TbMDecisionEntity> findAllByOrderByDecisionCodeAsc();
}
