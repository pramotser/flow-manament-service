package com.kiatnakinbank.naos.flowmanagementservice.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateResultParam;
import com.kiatnakinbank.naos.flowmanagementservice.dto.ResultParamDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.service.ResultParamService;

@RestController
@RequestMapping("/resultParam")
public class ResultParamController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultParamController.class);

    @Autowired
    private ResultParamService resultParamService;

    @PostMapping(value = "/getResulParamByCondition")
    public ResponseEntity<Response> getFlowListByCondition(HttpServletRequest request,
            @RequestBody Map<String, String> requestBody) {
        LOGGER.info("============ ResultParamController getResulParamByCondition ============");
        List<ResultParamDto> flowDtoList = this.resultParamService
                .getResultParamListByCondition(requestBody.get("resultParamName"));
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Success", flowDtoList));
    }

    @PostMapping(value = "/createResultParam")
    public ResponseEntity<Response> createResultParam(HttpServletRequest request,
            @RequestBody RequestCreateResultParam requestBody) {
        return this.resultParamService.createResultParam(requestBody);
    }

}
