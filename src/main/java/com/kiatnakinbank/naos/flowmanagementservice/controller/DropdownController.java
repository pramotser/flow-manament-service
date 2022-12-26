package com.kiatnakinbank.naos.flowmanagementservice.controller;

import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.dto.FlowDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.service.DropdownService;
import com.kiatnakinbank.naos.flowmanagementservice.service.FlowService;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dropdown")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DropdownController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownController.class);

    @Autowired
    private DropdownService dropdownService;

    @GetMapping(value = "/flowList")
    public ResponseEntity<Response> getDropdownFlowList() {
        LOGGER.info("============ Enter getDropdownFlowList ============");
        return Util.createResponse(Constants.ResponseCode.OK,this.dropdownService.getFLowList());
    }

    @GetMapping(value = "/resultParamList")
    public ResponseEntity<Response> getDropdownResultParamList() {
        LOGGER.info("============ Enter getDropdownResultParamList ============");
        return Util.createResponse(Constants.ResponseCode.OK,this.dropdownService.getResultParamList());
    }
}
