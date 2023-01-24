package com.kiatnakinbank.naos.flowmanagementservice.dto.decision;

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
public class ReqCreateDecision {
    @NotNull
    private String decisionCode;
    @NotBlank
    private String decisionName;
    @NotBlank
    private String decisionResult;
    @NotBlank
    private ActiveFlag isActive;
}
