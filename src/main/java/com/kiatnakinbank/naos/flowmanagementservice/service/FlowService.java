package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.FlowUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateFlow;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowEntity;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@Service
public class FlowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowService.class);
    @Autowired
    private FlowUnit flowUnit;

    public List<FlowDto> getFlowListByFlowId(String flowId) {
        LOGGER.info("============ FlowService getFlowListByFlowId ============");
        return this.flowUnit.getFlowListByFlowId(flowId);
    }

    public ResponseEntity<Response> createFlow(RequestCreateFlow requestCreateFlow) {
        LOGGER.info("============ FlowService createFlow ============");
        if (flowUnit.checkDuplicateFlow(requestCreateFlow.getFlowId())) {
            return Util.createResponse(Constants.ResponseCode.CONFLICT, "FLow Id is Duplicate.",
                    new ArrayList<>());
        }
        TbMFlowEntity tbmFlowEntity = mapTbMFlow(requestCreateFlow);
        tbmFlowEntity.setCreateAttribute("SYSTEM");
        tbmFlowEntity.setIsActive(requestCreateFlow.getIsActive());
        flowUnit.saveFlow(tbmFlowEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Create Flow Success", new ArrayList<>());
    }

    public ResponseEntity<Response> updateFlow(RequestCreateFlow requestCreateFlow) {
        LOGGER.info("============ FlowService updateFlow ============");
        if (!flowUnit.checkDuplicateFlow(requestCreateFlow.getFlowId())) {
            return Util.createResponse(Constants.ResponseCode.NOT_MODIFIED, "Flow ID is Data Not Found.",
                    new ArrayList<>());
        }
        TbMFlowEntity tbmFlowEntity = flowUnit.getTbmFlowByFlowId(requestCreateFlow.getFlowId());
        tbmFlowEntity.setFlowName(requestCreateFlow.getFlowName());
        tbmFlowEntity.setFlowResultParam(requestCreateFlow.getResultParam());
        tbmFlowEntity.setDecisionFlow(requestCreateFlow.getDecisionFlow());
        tbmFlowEntity.setIsActive(requestCreateFlow.getIsActive());
        tbmFlowEntity.setUpdateAttribute("SYSTEM");
        flowUnit.saveFlow(tbmFlowEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Update Flow Success", new ArrayList<>());
    }

    public ResponseEntity<Response> deleteFlow(RequestCreateFlow requestCreateFlow) {
        LOGGER.info("============ FlowService deleteFlow ============");
        if (!flowUnit.checkDuplicateFlow(requestCreateFlow.getFlowId())) {
            return Util.createResponse(Constants.ResponseCode.NOT_MODIFIED, "Flow ID is Data Not Found.",
                    new ArrayList<>());
        }
        if (!flowUnit.checkFlowInactive(requestCreateFlow.getFlowId())) {
            return Util.createResponse(Constants.ResponseCode.ACCEPTED, "Cannot delete because the status flow is active.",
                    new ArrayList<>());
        }
        flowUnit.deleteFlow(requestCreateFlow.getFlowId());
        return Util.createResponse(Constants.ResponseCode.OK, "Delete Flow Success.", new ArrayList<>());
    }

    private FlowDto mapTbmFlowToFlowDto(TbMFlowEntity tbmFlowEntity) {
        return new FlowDto(tbmFlowEntity.getFlowId(), tbmFlowEntity.getFlowName(), tbmFlowEntity.getFlowResultParam(),
                tbmFlowEntity.getStartFlowId(), tbmFlowEntity.getDecisionFlow(), tbmFlowEntity.getIsActive(),
                tbmFlowEntity.getCreateDate(), tbmFlowEntity.getCreateUser(), tbmFlowEntity.getUpdateDate(),
                tbmFlowEntity.getUpdateUser());
    }

    public TbMFlowEntity mapTbMFlow(RequestCreateFlow requestCreateFlow) {
        return new TbMFlowEntity(requestCreateFlow.getFlowId(), requestCreateFlow.getFlowName(),
                requestCreateFlow.getResultParam(), null, requestCreateFlow.getDecisionFlow());
    }
}
