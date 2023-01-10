package com.kiatnakinbank.naos.flowmanagementservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMUniversalFieldEntity;

@Repository
public interface TbMUniversalFieldRepository extends JpaRepository<TbMUniversalFieldEntity, String> {

    List<TbMUniversalFieldRepository> findAllByOrderByUniversalFieldCodeAsc();
}
