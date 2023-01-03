package com.kiatnakinbank.naos.flowmanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMResultParamEntity;

@Repository
public interface TbMResultParamRepository extends JpaRepository<TbMResultParamEntity, String> {
    List<TbMResultParamEntity> findByResultParamNameContainingIgnoreCase(String resultParam);

    long countByResultParamCode(String resultParamCode);

    List<TbMResultParamEntity> findByResultParamCode(String resultParamCode);

}
