package com.kiatnakinbank.naos.flowmanagementservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.DropdownResponse;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.service.DropdownService;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@RestController
@RequestMapping("/dropdown")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DropdownController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DropdownController.class);

    @Autowired
    private DropdownService dropdownService;

    @GetMapping(value = "/getList")
    public ResponseEntity<Response> getDropdownList(HttpServletRequest request) {
        LOGGER.info("============ DropdownController getList ============");
        if (!this.dropdownService.validateHeaderDropdown(request)) {
            return Util.createResponse(Constants.ResponseCode.BAD_REQUEST, "Missing or Invalid Required Field Header",
                    new ArrayList<>());
        }
        List<DropdownResponse> dropdownList = this.dropdownService.getDropdownByType(request.getHeader(Constants.HeaderKey.DROPDOWN_TYPE));
        if (dropdownList.isEmpty()) {
            return Util.createResponse(Constants.ResponseCode.OK, "Data Not Found", new ArrayList<>());
        }
        return Util.createResponse(Constants.ResponseCode.OK, "Success",
                dropdownList);
    }
}