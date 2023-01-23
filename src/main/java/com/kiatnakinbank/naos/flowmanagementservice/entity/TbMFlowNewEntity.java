package com.kiatnakinbank.naos.flowmanagementservice.entity;
import java.util.Date;

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
@Table(name = "TB_M_FLOW_NEW")
public class TbMFlowNewEntity extends BaseEntity {

    private static final long serialVersionUID = 820283650299533174L;

    @Id
    @Column(name = "FLOW_CODE", nullable = false, insertable = true, updatable = true, precision = 0)
    private String flowCode;
    @Column(name = "FLOW_NAME")
    private String flowName;

    @Column(name = "FLOW_DECISION_CODE")
    private String flowDecisionCode;

    @Column(name = "FLOW_START_NODE")
    private String flowStartNode;

    @Column(name = "FLOW_EFFECTIVE_DATE")
    private Date flowEffectiveDate;

    @Column(name = "FLOW_EXPIRATION_DATE")
    private Date flowExpirationDate;

    @Column(name = "FLOW_JSON")
    private String flowJson;
}
