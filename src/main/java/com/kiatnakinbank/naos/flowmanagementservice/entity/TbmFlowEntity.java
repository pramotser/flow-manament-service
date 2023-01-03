package com.kiatnakinbank.naos.flowmanagementservice.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

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
@Table(name = "TB_M_FLOW_TEMP")
public class TbmFlowEntity extends BaseEntity {

    private static final long serialVersionUID = 820283650299533174L;

    @Id
    @Column(name = "FLOW_ID", nullable = false, insertable = true, updatable = true, precision = 0)
    private Long flowId;
    @Column(name = "FLOW_NAME")
    private String flowName;
    @Column(name = "RESULT_PARAM")
    private String flowResultParam;
    @Column(name = "START_FLOW_ID")
    private Long startFlowId;
    @Nationalized
    @Column(name = "DECISION_FLOW")
    private String decisionFlow;
//    @Column(name = "IS_ACTIVE")
//    private String isActive;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "CREATE_DATE")
//    private Date createDate;
//    @Column(name = "CREATE_USER")
//    private String createUser;
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "UPDATE_DATE")
//    private Date updateDate;
//    @Column(name = "UPDATE_USER")
//    private String updateUser;
}
