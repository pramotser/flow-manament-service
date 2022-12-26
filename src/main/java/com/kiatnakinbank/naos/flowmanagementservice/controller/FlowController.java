package com.kiatnakinbank.naos.flowmanagementservice.controller;

import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateFlow;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.service.FlowService;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/flow")
public class FlowController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private FlowService flowService;

    @PostMapping(value = "/getFlowListByCondition")
    public ResponseEntity<Response> getFlowListByCondition(@RequestHeader Map<String, String> headers,
                                                           @RequestBody Map<String, String> request) {
        LOGGER.info("============ Enter getFlowListByCondition ============");
        List<FlowDto> flowDtoList = this.flowService.getFlowListByCondition(request.get("flowName"));
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Success", flowDtoList));
    }


//    @PostMapping(value = "/createFlow")
//    public ResponseEntity<Response> createFlow(@RequestHeader Map<String, String> headers,
//                                               @RequestBody RequestCreateFlow request) {
//        return Util.createResponse(Constants.ResponseCode.OK, this.flowService.createFlow(request));
//    }

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

}
