package com.kiatnakinbank.naos.flowmanagementservice.dto;

import java.util.Date;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class FlowDto {
    private Long flowId;
    private String flowName;
    private String resultParam;
    private Long startFlowId;
    private String decisionFlow;
    private ActiveFlag isActive;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;
}
