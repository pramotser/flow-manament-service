package com.kiatnakinbank.naos.flowmanagementservice.dto.flow;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.common.framework.util.JsonDateDeserializer;
import com.kiatnakinbank.naos.common.framework.util.JsonDateSerializer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class ReqFlowDto {
    private String flowCode;

    @NotNull
    private String flowName;

    @NotBlank
    private String flowDecisionCode;

    @JsonDeserialize(using = JsonDateDeserializer.class)
    @JsonSerialize(using = JsonDateSerializer.class)
    @NotNull
    private Date flowEffectiveDate;
    
    private String flowJson;

    @NotBlank
    private ActiveFlag isActive;
}
