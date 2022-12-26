package com.kiatnakinbank.naos.flowmanagementservice.service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.FlowUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateFlow;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbmFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlowService {

    @Autowired
    private FlowUnit flowUnit;

    public List<FlowDto> getFlowListByCondition(String flowName) {
        return flowUnit.getFlowListByCondition(flowName);
    }

    public ResponseEntity<Response> createFlow(RequestCreateFlow requestCreateFlow) {
        if (flowUnit.checkDuplicateFlow(requestCreateFlow.getFlowId())) {
            return Util.createResponse(Constants.ResponseCode.CONFLICT, new ArrayList<>());
        }
        TbmFlowEntity tbmFlowEntity = mapTbMFlow(requestCreateFlow);
        tbmFlowEntity.setCreateAttribute("SYSTEM");
        tbmFlowEntity.setIsActive(requestCreateFlow.getIsActive());
        return Util.createResponse(Constants.ResponseCode.OK, mapTbmFlowToFlowDto(flowUnit.saveFlow(tbmFlowEntity)));
    }

    public ResponseEntity<Response> updateFlow(RequestCreateFlow requestCreateFlow) {
        if (!flowUnit.checkDuplicateFlow(requestCreateFlow.getFlowId())) {
            return Util.createResponse(Constants.ResponseCode.NOT_MODIFIED, new ArrayList<>());
        }
        TbmFlowEntity tbmFlowEntity = flowUnit.getTbmFlowByFlowId(requestCreateFlow.getFlowId());
        tbmFlowEntity.setFlowName(requestCreateFlow.getFlowName());
        tbmFlowEntity.setFlowResultParam(requestCreateFlow.getResultParam());
        tbmFlowEntity.setDecisionFlow(requestCreateFlow.getDecisionFlow());
        tbmFlowEntity.setIsActive(requestCreateFlow.getIsActive());
        tbmFlowEntity.setUpdateAttribute("SYSTEM");
        return Util.createResponse(Constants.ResponseCode.OK, mapTbmFlowToFlowDto(flowUnit.saveFlow(tbmFlowEntity)));
    }

    private FlowDto mapTbmFlowToFlowDto(TbmFlowEntity tbmFlowEntity) {
        return new FlowDto(tbmFlowEntity.getFlowId(), tbmFlowEntity.getFlowName(), tbmFlowEntity.getFlowResultParam(), tbmFlowEntity.getStartFlowId(), tbmFlowEntity.getDecisionFlow(), tbmFlowEntity.getIsActive(), tbmFlowEntity.getCreateDate(), tbmFlowEntity.getCreateUser(), tbmFlowEntity.getUpdateDate(), tbmFlowEntity.getUpdateUser());
    }

    public TbmFlowEntity mapTbMFlow(RequestCreateFlow requestCreateFlow) {
        return new TbmFlowEntity(requestCreateFlow.getFlowId(), requestCreateFlow.getFlowName(), requestCreateFlow.getResultParam(), null, requestCreateFlow.getDecisionFlow());
    }
}
