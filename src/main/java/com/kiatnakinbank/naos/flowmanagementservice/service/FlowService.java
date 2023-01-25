package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.common.framework.enums.ActiveFlag;
import com.kiatnakinbank.naos.flowmanagementservice.bizunit.DecisionUnit;
import com.kiatnakinbank.naos.flowmanagementservice.bizunit.FlowUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.FlowListDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.ReqFlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.ReqSaveFlowGraph;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMFlowNewEntity;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@Service
public class FlowService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlowService.class);
    @Autowired
    private FlowUnit flowUnit;

    @Autowired
    private DecisionUnit decisionUnit;

    /// ----------------------- New -----------------------
    public ResponseEntity<Response> addFlow(ReqFlowDto requestBody) {
        if (!this.decisionUnit.checkDecisionCodeIsNotNull(requestBody.getFlowDecisionCode())) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Decision Code is Data Not Found.",
                    new ArrayList<>());
        }
        if(this.flowUnit.checkFlowNameDuplicate(requestBody.getFlowName(),requestBody.getFlowDecisionCode())){
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Flow Name is Duplicate.",
            new ArrayList<>());
        }
        String flowCode = this.flowUnit.generateFlowCodeByDecisionCode(requestBody.getFlowDecisionCode());
        TbMFlowNewEntity tbMFlowNewEntity = new TbMFlowNewEntity();
        tbMFlowNewEntity.setFlowCode(flowCode);
        tbMFlowNewEntity.setFlowDecisionCode(requestBody.getFlowDecisionCode());
        tbMFlowNewEntity.setFlowName(requestBody.getFlowName());
        tbMFlowNewEntity.setFlowEffectiveDate(requestBody.getFlowEffectiveDate());
        tbMFlowNewEntity.setCreateAttribute("SYSTEM");
        tbMFlowNewEntity.setIsActive(requestBody.getIsActive());
        tbMFlowNewEntity.setFlowJson(requestBody.getFlowJson());
        this.flowUnit.saveFlow(tbMFlowNewEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Add Flow Success", flowCode);
    }

    public List<FlowListDto> getFlowListByDecisionCode(String flowDecisionCode) {
        return this.flowUnit.getFlowListByDecisionCode(flowDecisionCode);
    }

    public ResponseEntity<Response> saveFlowGraph(ReqSaveFlowGraph requestBody) {
        if (!this.flowUnit.checkFlowCodeIsNotNull(requestBody.getFlowCode())) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Flow Code is Data Not Found.",
                    new ArrayList<>());
        }

        TbMFlowNewEntity tbMFlowNewEntity = this.flowUnit.getTbMFlowNewByFlowCode(requestBody.getFlowCode());
        tbMFlowNewEntity.setFlowJson(requestBody.getFlowJson());
        tbMFlowNewEntity.setUpdateAttribute("SYSTEM");
        flowUnit.saveFlow(tbMFlowNewEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Save Flow Graph Success", new ArrayList<>());
    }

    public ResponseEntity<Response> updateFlow(ReqFlowDto requestBody) {
        if (!this.flowUnit.checkFlowCodeIsNotNull(requestBody.getFlowCode())) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Flow Code is Data Not Found.",
                    new ArrayList<>());
        }
        if (!this.decisionUnit.checkDecisionCodeIsNotNull(requestBody.getFlowDecisionCode())) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Decision Code is Data Not Found.",
                    new ArrayList<>());
        }
        TbMFlowNewEntity tbMFlowNewEntity = this.flowUnit.getTbMFlowNewByFlowCode(requestBody.getFlowCode());
        tbMFlowNewEntity.setFlowName(requestBody.getFlowName());
        tbMFlowNewEntity.setFlowEffectiveDate(requestBody.getFlowEffectiveDate());
        tbMFlowNewEntity.setUpdateAttribute("SYSTEM");
        flowUnit.saveFlow(tbMFlowNewEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Update Flow Success", new ArrayList<>());
    }

    public ResponseEntity<Response> deleteFlow(String flowCode) {
        if (!this.flowUnit.checkFlowCodeIsNotNull(flowCode)) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Flow Code is Data Not Found.",
                    new ArrayList<>());
        }
        if (this.flowUnit.checkFlowCodeActiveByFlowCode(flowCode)) {
            return Util.createResponse(Constants.ResponseCode.ACCEPTED,
                    "Cannot delete because the status flow is active.",
                    new ArrayList<>());
        }
        flowUnit.deleteFlowByFlowCode(flowCode);
        return Util.createResponse(Constants.ResponseCode.OK, "Delete Flow Success.", new ArrayList<>());
    }

    public ResponseEntity<Response> activeFlow(ReqFlowDto requestBody) {
        LOGGER.info("checkGraphFlowIsNotNull" + !this.flowUnit.checkFlowCodeIsNotNull(requestBody.getFlowCode()));
        if (!this.flowUnit.checkFlowCodeIsNotNull(requestBody.getFlowCode())) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Flow Code is Data Not Found.",
                    new ArrayList<>());
        }
        LOGGER.info(("checkGraphFlowIsNotNull" + !this.flowUnit.checkGraphFlowIsNotNull(requestBody.getFlowCode())));
        if (!this.flowUnit.checkGraphFlowIsNotNull(requestBody.getFlowCode())) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "This Flow Graph is Not Found.",
                    new ArrayList<>());
        }
        if (!this.flowUnit.checkEffectDate(requestBody.getFlowCode(), requestBody.getFlowDecisionCode())) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "This Flow Effective Date is incorrect.",
                    new ArrayList<>());
        }
        TbMFlowNewEntity tbMFlowNewEntity = this.flowUnit.getTbMFlowNewByFlowCode(requestBody.getFlowCode());

        // Chnage exp flow active old to new effect - 1 day
        TbMFlowNewEntity flowOldActive = this.flowUnit.getTbMFlowNewLastActiveByDecisionCode(requestBody.getFlowDecisionCode());
        flowOldActive.setUpdateAttribute("SYSTEM");
        flowOldActive.setFlowExpirationDate(tbMFlowNewEntity.getFlowEffectiveDate());
        this.flowUnit.saveFlow(flowOldActive);

        // Save new flow active 
        tbMFlowNewEntity.setIsActive(ActiveFlag.Y);
        this.flowUnit.saveFlow(tbMFlowNewEntity);

        return Util.createResponse(Constants.ResponseCode.OK, "Active Flow Success.", new ArrayList<>());
    }
}
