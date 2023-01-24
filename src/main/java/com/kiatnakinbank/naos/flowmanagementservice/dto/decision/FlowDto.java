package com.kiatnakinbank.naos.flowmanagementservice.dto.decision;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
public class FlowDto {
    
    private String flowCode;
    private String flowDecisionCode;    
    @JsonDeserialize(using= JsonDateDeserializer.class)
    @JsonSerialize(using= JsonDateSerializer.class)
    private Date flowEffectiveDate;
    @JsonDeserialize(using= JsonDateDeserializer.class)
    @JsonSerialize(using= JsonDateSerializer.class)
    private Date flowExpirationDate;
    private String flowName;
}
