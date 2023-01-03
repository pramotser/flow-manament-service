package com.kiatnakinbank.naos.flowmanagementservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kiatnakinbank.naos.flowmanagementservice.entity.base.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@Table(name = "TB_M_RESULT_PARAM_TEMP")
public class TbMResultParamEntity extends BaseEntity {
    @Id
    @Column(name = "RESULT_PARAM_CODE", nullable = false, insertable = true, updatable = true, precision = 0)
    private String resultParamCode;
    @Column(name = "RESULT_PARAM_NAME")
    private String resultParamName;
}
