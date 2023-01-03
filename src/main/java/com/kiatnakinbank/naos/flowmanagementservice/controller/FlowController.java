package com.kiatnakinbank.naos.flowmanagementservice.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateFlow;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.service.FlowService;

@RestController
@RequestMapping("/flow")
public class FlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private FlowService flowService;

    @PostMapping(value = "/getFlowListByCondition")
    public ResponseEntity<Response> getFlowListByCondition(HttpServletRequest request,
            @RequestBody Map<String, String> requestBody) {
        LOGGER.info("============ FlowController getFlowListByCondition ============");
        List<FlowDto> flowDtoList = this.flowService.getFlowListByCondition(requestBody.get("flowName"));
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Success", flowDtoList));
    }

    @PostMapping(value = "/createFlow")
    public ResponseEntity<Response> createFlow(@RequestHeader Map<String, String> headers,
            @RequestBody RequestCreateFlow request) {
        return this.flowService.createFlow(request);
    }

    @PostMapping(value = "/updateFlow")
    public ResponseEntity<Response> updateFlow(@RequestHeader Map<String, String> headers,
            @RequestBody RequestCreateFlow request) {
        return this.flowService.updateFlow(request);
    }

    @DeleteMapping(value = "/deleteFlow")
    public ResponseEntity<Response> deleteFlow(@RequestHeader Map<String, String> headers,
            @RequestBody RequestCreateFlow request) {
        return this.flowService.deleteFlow(request);
    }
}
