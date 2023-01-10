package com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class DropdownResponse {
    private String value;
    private String label;
    private Object data;
}
