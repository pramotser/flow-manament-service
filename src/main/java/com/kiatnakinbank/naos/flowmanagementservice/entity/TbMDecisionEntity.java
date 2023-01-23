package com.kiatnakinbank.naos.flowmanagementservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.kiatnakinbank.naos.flowmanagementservice.entity.base.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
@Entity
@Table(name = "TB_M_DECISION_NEW")
public class TbMDecisionEntity extends BaseEntity {
    private static final long serialVersionUID = 820283650299533174L;
    @Id
    @Column(name = "DECISION_CODE", nullable = false, insertable = true, updatable = true, precision = 0)
    private String decisionCode;
    @Column(name = "DECISION_NAME")
    private String decisionName;
    @Column(name = "DECISION_RESULT")
    private String decisionResult;
}
