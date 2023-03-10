package com.kiatnakinbank.naos.flowmanagementservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
public class RequestCreateFlow {
    @NotNull
    private Long flowId;
    @NotBlank
    private String flowName;
    @NotBlank
    private String resultParam;

    private String decisionFlow;
    @NotBlank
    private ActiveFlag isActive;
}
