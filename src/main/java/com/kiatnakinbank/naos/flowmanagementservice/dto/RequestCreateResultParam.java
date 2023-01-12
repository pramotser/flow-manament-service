package com.kiatnakinbank.naos.flowmanagementservice.dto;

import javax.validation.constraints.NotBlank;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;

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
public class RequestCreateResultParam {
    
    @NotBlank
    private String resultParamCode;
    @NotBlank
    private String resultParamName;
    @NotBlank
    private String resultParamType;
    @NotBlank
    private ActiveFlag isActive;
}
