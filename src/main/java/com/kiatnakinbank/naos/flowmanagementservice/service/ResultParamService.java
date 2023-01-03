package com.kiatnakinbank.naos.flowmanagementservice.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kiatnakinbank.naos.flowmanagementservice.bizunit.ResultParamUnit;
import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateResultParam;
import com.kiatnakinbank.naos.flowmanagementservice.dto.ResultParamDto;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMResultParamEntity;
import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

@Service
public class ResultParamService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResultParamService.class);
    @Autowired
    private ResultParamUnit resultParamUnit;

    public List<ResultParamDto> getResultParamListByCondition(String resultParamName) {
        LOGGER.info("============ ResultParamService getResultParamListByCondition ============");
        return this.resultParamUnit.getResultParamByCondition(resultParamName);
    }

    public ResponseEntity<Response> createResultParam(RequestCreateResultParam requestBody) {
        if (this.resultParamUnit.duplicateResultParamCode(requestBody.getResultParamCode())) {
            Util.createResponse(Constants.ResponseCode.CONFLICT, "Result Param Code is Duplicate.", new ArrayList<>());
        }
        TbMResultParamEntity tbMResultParamEntity = mapTbMResultParam(requestBody);
        tbMResultParamEntity.setCreateAttribute("SYSTEM");
        tbMResultParamEntity.setIsActive(requestBody.getIsActive());

        this.resultParamUnit.saveTbMResultParamEntity(tbMResultParamEntity);
        return Util.createResponse(Constants.ResponseCode.OK, "Create Result Param Success", new ArrayList<>());
    }

    private TbMResultParamEntity mapTbMResultParam(RequestCreateResultParam requestCreateResultParam) {
        return new TbMResultParamEntity(requestCreateResultParam.getResultParamCode(),
                requestCreateResultParam.getResultParamName());
    }
}
