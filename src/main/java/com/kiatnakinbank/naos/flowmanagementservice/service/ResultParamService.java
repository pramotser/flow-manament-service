// package com.kiatnakinbank.naos.flowmanagementservice.service;

// import java.util.ArrayList;
// import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Service;

// import com.kiatnakinbank.naos.flowmanagementservice.bizunit.FlowUnit;
// import com.kiatnakinbank.naos.flowmanagementservice.bizunit.ResultParamUnit;
// import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
// import com.kiatnakinbank.naos.flowmanagementservice.dto.RequestCreateResultParam;
// import com.kiatnakinbank.naos.flowmanagementservice.dto.ResultParamDto;
// import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
// import com.kiatnakinbank.naos.flowmanagementservice.dto.dropdown.DropdownResponse;
// import com.kiatnakinbank.naos.flowmanagementservice.entity.TbMResultParamEntity;
// import com.kiatnakinbank.naos.flowmanagementservice.util.Util;

// @Service
// public class ResultParamService {
//     private static final Logger LOGGER = LoggerFactory.getLogger(ResultParamService.class);
//     @Autowired
//     private ResultParamUnit resultParamUnit;
//     @Autowired
//     private FlowUnit flowUnit;

//     public List<ResultParamDto> getResultParamListByCondition(String resultParamName) {
//         LOGGER.info("============ ResultParamService getResultParamListByCondition ============");
//         return this.resultParamUnit.getResultParamByCondition(resultParamName);
//     }

//     public ResponseEntity<Response> createResultParam(RequestCreateResultParam requestBody) {
//         LOGGER.info("============ ResultParamService createResultParam ============");
//         if (this.resultParamUnit.duplicateResultParamCode(requestBody.getResultParamCode())) {
//             return Util.createResponse(Constants.ResponseCode.CONFLICT, "Result Param Code is Duplicate.",
//                     new ArrayList<>());
//         }
//         TbMResultParamEntity tbMResultParamEntity = mapTbMResultParam(requestBody);
//         tbMResultParamEntity.setCreateAttribute("SYSTEM");
//         tbMResultParamEntity.setIsActive(requestBody.getIsActive());
//         this.resultParamUnit.saveTbMResultParamEntity(tbMResultParamEntity);
//         return Util.createResponse(Constants.ResponseCode.OK, "Create Result Param Success", new ArrayList<>());
//     }

//     public ResponseEntity<Response> deleteResultParam(String resultParamCode) {
//         LOGGER.info("============ ResultParamService deleteResultParam ============");
//         if (!this.resultParamUnit.duplicateResultParamCode(resultParamCode)) {
//             return Util.createResponse(Constants.ResponseCode.NOT_MODIFIED, "Result Param Code is Data Not Found.",
//                     new ArrayList<>());
//         }
//         if (!this.resultParamUnit.checkResultParamInactive(resultParamCode)) {
//             return Util.createResponse(Constants.ResponseCode.ACCEPTED, "Resutl param must be inactive.",
//                     new ArrayList<>());
//         }
//         this.resultParamUnit.deleteResultParamByResultParamCode(resultParamCode);
//         return Util.createResponse(Constants.ResponseCode.OK, "Delete Result Param Success.", new ArrayList<>());
//     }

//     public ResponseEntity<Response> updateResultParam(RequestCreateResultParam requestBody) {
//         LOGGER.info("============ ResultParamService updateResultParam ============");
//         if (!this.resultParamUnit.duplicateResultParamCode(requestBody.getResultParamCode())) {
//             return Util.createResponse(Constants.ResponseCode.NOT_MODIFIED, "Result Param Code is Data Not Found.",
//                     new ArrayList<>());
//         }
//         TbMResultParamEntity tbMResultParamEntity = resultParamUnit
//                 .getTbMResultParamById(requestBody.getResultParamCode());
//         if (this.resultParamUnit.checkResultParamUsing(requestBody.getResultParamCode())) {
//             if (!tbMResultParamEntity.getIsActive().equals(requestBody.getIsActive())) {
//                 return Util.createResponse(Constants.ResponseCode.ACCEPTED,
//                         "Result Param is currently in use, cannot be changed to Inactive.", new ArrayList<>());
//             }
//             // if
//             // (!requestBody.getResultParamType().equalsIgnoreCase(tbMResultParamEntity.getResultParamType())){
//             // return Util.createResponse(Constants.ResponseCode.ACCEPTED,
//             // "Result Param is currently in use, cannot be changed to Result Param Type.",
//             // new ArrayList<>());
//             // }
//         }
//         tbMResultParamEntity.setResultParamName(requestBody.getResultParamName());
//         tbMResultParamEntity.setResultParamType(requestBody.getResultParamType());
//         tbMResultParamEntity.setIsActive(requestBody.getIsActive());
//         tbMResultParamEntity.setUpdateAttribute("SYSTEM");
//         this.resultParamUnit.saveTbMResultParamEntity(tbMResultParamEntity);
//         return Util.createResponse(Constants.ResponseCode.OK, "Update Result Param Success", new ArrayList<>());
//     }

//     private TbMResultParamEntity mapTbMResultParam(RequestCreateResultParam requestCreateResultParam) {
//         return new TbMResultParamEntity(requestCreateResultParam.getResultParamCode(),
//                 requestCreateResultParam.getResultParamName(),
//                 requestCreateResultParam.getResultParamType());
//     }

//     public ResponseEntity<Response> getResulltParamByFlowId(String flowId) {
//         LOGGER.info("============ ResultParamService getResulltParamByFlowId ============");
//         String resultParam = flowUnit.getResultParamCodeByFLowId(flowId);
//         return Util.createResponse(Constants.ResponseCode.OK, "Success", this.mapTbMResultParamEntityToDropdownResponse(this.resultParamUnit.getTbMResultParamById(resultParam)));
//     }

//     private DropdownResponse mapTbMResultParamEntityToDropdownResponse(TbMResultParamEntity tbMResultParamEntity) {
//         return new DropdownResponse(tbMResultParamEntity.getResultParamCode(),
//                 tbMResultParamEntity.getResultParamName(), tbMResultParamEntity);
//     }
// }
