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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.dto.universalField.RequestCreateUniversalField;
import com.kiatnakinbank.naos.flowmanagementservice.dto.universalField.UniversalFieldDto;
import com.kiatnakinbank.naos.flowmanagementservice.service.UniversalService;

@RestController
@RequestMapping("/universal")
public class UniversalController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UniversalController.class);

    @Autowired
    private UniversalService universalService;

    @PostMapping(value = "/getUniversalListByCondition")
    public ResponseEntity<Response> getUniversalListByCondition(HttpServletRequest request,
            @RequestBody Map<String, String> requestBody) {
        LOGGER.info("============ UniversalController getUniversalListByCondition ============");
        List<UniversalFieldDto> universalFieldDtoList = this.universalService
                .getUniversalListByCondition(requestBody.get("universalName"));
        return ResponseEntity.status(HttpStatus.OK).body(new Response(HttpStatus.OK, "Success", universalFieldDtoList));
    }

    @PostMapping(value = "/createUniversalField")
    public ResponseEntity<Response> createUniversalField(HttpServletRequest request,
            @RequestBody RequestCreateUniversalField requestBody) {
        LOGGER.info("============ UniversalController createUniversalField ============");
        return this.universalService.createUniversalField(requestBody);
    }

    @PostMapping(value = "/updateUniversalField")
    public ResponseEntity<Response> updateUniversalField(HttpServletRequest request,
            @RequestBody RequestCreateUniversalField requestBody) {
        LOGGER.info("============ UniversalController updateUniversalField ============");
        return this.universalService.updateUniversalField(requestBody);
    }

    @DeleteMapping(value = "/deleteUniversalField")
    public ResponseEntity<Response> deleteUniversalField(HttpServletRequest request,
            @RequestBody RequestCreateUniversalField requestBody) {
        LOGGER.info("============ UniversalController deleteUniversalField ============");
        return this.universalService.deleteUniversalField(requestBody.getUniversalCode());
    }

    @PostMapping(value = "/getDecisionResultByDecisionCode")
    public ResponseEntity<Response> getDecisionResultByDecisionCode(HttpServletRequest request,
            @RequestBody Map<String, String> requestBody) {
        return this.universalService.getDecisionResultByDecisionCode(requestBody.get("decisionCode"));
    }
}
