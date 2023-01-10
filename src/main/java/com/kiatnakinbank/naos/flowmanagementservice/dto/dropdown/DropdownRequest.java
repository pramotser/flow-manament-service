package com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown;



import javax.validation.constraints.NotBlank;

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
public class DropdownRequest {
    @NotBlank
    public String dropdownType;
    public ActiveFlag flagShowCode;
}
