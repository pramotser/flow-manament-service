package com.kiatnakinbank.naos.flowmanagementservice.dto.decision;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class DecisionDto {

    @NotBlank
    private String decisionName;
    @NotBlank
    private String decisionCode;
    @NotBlank
    private String decisionResult;
    @NotBlank
    private ActiveFlag isActive;
    
    private Date createDate;
    private String createUser;
    private Date updateDate;
    private String updateUser;
}
