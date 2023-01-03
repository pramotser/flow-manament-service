package com.kiatnakinbank.naos.flowmanagementservice.repository;

import com.kiatnakinbank.naos.flowmanagementservice.entity.TbmFlowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbMFlowRepository extends JpaRepository<TbmFlowEntity, Long> {
    List<TbmFlowEntity> findByFlowNameContainingIgnoreCase(String flowName);

    @Query(value = " SELECT DISTINCT RESULT_PARAM as FlowResultParam " +
            " FROM TB_M_FLOW_TEMP", nativeQuery = true)
    List<String> findDistinctFlowResultParam();

    long countByFlowId(Long flowId);

    List<TbmFlowEntity> findByFlowId(long flowId);

    long countByFlowResultParam(String flowResultParam);
}
