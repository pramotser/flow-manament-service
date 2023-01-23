package com.kiatnakinbank.naos.flowmanagementservice.dto.flow;

import java.util.Date;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class FlowListDto {
    private String flowCode;
    private String flowName;
    private String flowDecisionCode;
    private Date flowEffectiveDate;
    private Date flowExpirationDate;
    private String flowJson;    
    private ActiveFlag isActive;
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;
}
