package com.kiatnakinbank.naos.flowmanagementservice.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.FlowListDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.ReqFlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.flow.ReqSaveFlowGraph;
import com.kiatnakinbank.naos.flowmanagementservice.service.FlowService;

@RestController
@RequestMapping("/flow")
public class FlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private FlowService flowService;
    /// ----------------------- New -----------------------
    @PostMapping(value = "/addFlow")
    public ResponseEntity<Response> addFlow(@RequestHeader Map<String, String> headers,
            @RequestBody ReqFlowDto request) {
        return this.flowService.addFlow(request);
    }

    @PostMapping(value = "/getFlowListByDecisionCode")
    public ResponseEntity<Response> getFlowListByDecisionCode(@RequestHeader Map<String, String> headers,
            @RequestBody Map<String, String> requestBody) {
        List<FlowListDto> flowDtoList = this.flowService.getFlowListByDecisionCode(requestBody.get("flowDecisionCode"));
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Success", flowDtoList));
    }

    @PostMapping(value = "/saveFlowGraph")
    public ResponseEntity<Response> saveFlowGraph(@RequestHeader Map<String, String> headers,
            @RequestBody ReqSaveFlowGraph requestBody) {
        return this.flowService.saveFlowGraph(requestBody);
    }

}
