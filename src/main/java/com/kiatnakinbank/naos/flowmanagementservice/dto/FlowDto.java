package com.kiatnakinbank.naos.flowmanagementservice.dto;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import lombok.*;

import java.util.Date;

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
