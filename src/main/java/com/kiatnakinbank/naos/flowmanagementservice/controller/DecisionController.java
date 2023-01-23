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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.dto.decision.DecisionDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.decision.ReqCreateDecision;
import com.kiatnakinbank.naos.flowmanagementservice.service.DecisionService;

@RestController
@RequestMapping("/decision")
public class DecisionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(DecisionController.class);

    @Autowired
    private DecisionService decisionService;

    @PostMapping(value = "/getDecisionListByCondition")
    public ResponseEntity<Response> getDecisionListByCondition(HttpServletRequest request,
            @RequestBody Map<String, String> requestBody) {
        LOGGER.info("============ DecisionController getDecisionListByCondition ============");
        List<DecisionDto> decisionList = this.decisionService
                .getDecisionListByCondition(requestBody.get("decisionName"));
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Success", decisionList));
    }

    @GetMapping(value = "/getNewDecisionCode")
    public ResponseEntity<Response> getNewDecisionCode(HttpServletRequest request) {
        LOGGER.info("============ DecisionController getNewDecisionCode ============");
        return this.decisionService.getNewDecisionCode();
    }

    @PostMapping(value = "/createDecision")
    public ResponseEntity<Response> createFlow(HttpServletRequest request,
            @RequestBody ReqCreateDecision requestBody) {
        return this.decisionService.createDecision(requestBody);
    }

    @PostMapping(value = "/updateDecision")
    public ResponseEntity<Response> updateDecision(HttpServletRequest request,
            @RequestBody ReqCreateDecision requestBody) {
        return this.decisionService.updateDecision(requestBody);
    }

    @DeleteMapping(value = "/deleteDecision")
    public ResponseEntity<Response> deleteFlow(HttpServletRequest request,
            @RequestBody ReqCreateDecision requestBody) {
        return this.decisionService.deleteDecision(requestBody);
    }
}
