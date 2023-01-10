package com.kiatnakinbank.naos.flowmanagementservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownRequest;
import com.kiatnakinbank.naos.flowmanagementservice.service.DropdownService;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@RestController
@RequestMapping("/dropdown")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DropdownController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownController.class);

    @Autowired
    private DropdownService dropdownService;

    @PostMapping(value = "/getListByCondition")
    public ResponseEntity<Response> getDropdownList(HttpServletRequest request,
            @RequestBody DropdownRequest requestBody) {
        LOGGER.info("============ DropdownController getListByCondition ============");
        if (!this.dropdownService.validateDropdownType(requestBody)) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Missing or Invalid Required Field Header",
                    new ArrayList<>());
        }
        List<DropdownResponse> dropdownList = this.dropdownService.getDropdownListByType(requestBody);
        if (dropdownList.isEmpty()) {
            return Util.createResponse(Constants.ResponseCode.OK, "Data Not Found", new ArrayList<>());
        }
        return Util.createResponse(Constants.ResponseCode.OK, "Success",
                dropdownList);
    }
}