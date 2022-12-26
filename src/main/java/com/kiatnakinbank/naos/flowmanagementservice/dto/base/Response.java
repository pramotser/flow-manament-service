package com.kiatnakinbank.naos.flowmanagementservice.dto.base;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Response {
    Object responseCode;
    Object responseDecription;
    Object responseObject;
}
