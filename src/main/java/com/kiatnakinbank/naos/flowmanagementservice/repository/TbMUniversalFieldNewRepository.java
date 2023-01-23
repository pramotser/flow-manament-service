package com.kiatnakinbank.naos.flowmanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMUniversalFieldNewEntity;

public interface TbMUniversalFieldNewRepository extends JpaRepository<TbMUniversalFieldNewEntity, String> {

    List<TbMUniversalFieldNewEntity> findByUniversalNameContainingIgnoreCase(String universalName);

    long countByUniversalCode(String universalCode);

    List<TbMUniversalFieldNewEntity> findByUniversalCode(String universalCode);

    List<TbMUniversalFieldNewEntity> findByUniversalType(String universalType);
}
