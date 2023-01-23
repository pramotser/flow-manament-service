package com.kiatnakinbank.naos.flowmanagementservice.dto.universalField;

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
public class RequestCreateUniversalField {
    
    @NotBlank
    private String universalCode;
    @NotBlank
    private String universalName;
    @NotBlank
    private String universalType;
    @NotBlank
    private String fieldType;
    @NotBlank
    private ActiveFlag isActive;
}
