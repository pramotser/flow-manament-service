package com.kiatnakinbank.naos.flowmanagementservice.util;

import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static ResponseEntity<Response> createResponse(int responseCode, String responseDescription,Object responseObject) {
        HttpStatus httpStatus = HttpStatus.valueOf(responseCode);
        Response response = new Response(responseCode, responseDescription, responseObject);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static ResponseEntity<Response> createResponseByCode(int responseCode, Object responseObject) {
        HttpStatus httpStatus = HttpStatus.OK;
        String responseDescription = "";
        if (responseCode == Constants.ResponseCode.OK) {
            responseDescription = Constants.ResponseDescription.OK;
        } else if (responseCode == Constants.ResponseCode.CREATED) {
            httpStatus = HttpStatus.CREATED;
            responseDescription = Constants.ResponseDescription.CREATED;
        } else if (responseCode == Constants.ResponseCode.ACCEPTED) {
            httpStatus = HttpStatus.ACCEPTED;
            responseDescription = Constants.ResponseDescription.ACCEPTED;
        } else if (responseCode == Constants.ResponseCode.NO_CONTENT) {
            httpStatus = HttpStatus.NO_CONTENT;
            responseDescription = Constants.ResponseDescription.NO_CONTENT;
        } else if (responseCode == Constants.ResponseCode.NOT_MODIFIED) {
            httpStatus = HttpStatus.NOT_MODIFIED;
            responseDescription = Constants.ResponseDescription.NOT_MODIFIED;
        } else if (responseCode == Constants.ResponseCode.BAD_REQUEST) {
            httpStatus = HttpStatus.BAD_REQUEST;
            responseDescription = Constants.ResponseDescription.BAD_REQUEST;
        } else if (responseCode == Constants.ResponseCode.UNAUTHORIZED) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            responseDescription = Constants.ResponseDescription.UNAUTHORIZED;
        } else if (responseCode == Constants.ResponseCode.FORBIDDEN) {
            httpStatus = HttpStatus.FORBIDDEN;
            responseDescription = Constants.ResponseDescription.FORBIDDEN;
        } else if (responseCode == Constants.ResponseCode.PAYLOAD_TOO_LARGE) {
            httpStatus = HttpStatus.PAYLOAD_TOO_LARGE;
            responseDescription = Constants.ResponseDescription.PAYLOAD_TOO_LARGE;
        } else if (responseCode == Constants.ResponseCode.UNPROCESSABLE_ENTITY) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            responseDescription = Constants.ResponseDescription.UNPROCESSABLE_ENTITY;
        } else if (responseCode == Constants.ResponseCode.INTERNAL_SERVER_ERROR) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDescription = Constants.ResponseDescription.INTERNAL_SERVER_ERROR;
        } else if (responseCode == Constants.ResponseCode.SERVICE_UNAVAILABLE) {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
            responseDescription = Constants.ResponseDescription.SERVICE_UNAVAILABLE;
        } else if( responseCode == Constants.ResponseCode.CONFLICT){
            httpStatus = HttpStatus.CONFLICT;
            responseDescription = Constants.ResponseDescription.CONFLICT;
        }
        Response response = new Response(responseCode, responseDescription, responseObject);
        return ResponseEntity.status(httpStatus).body(response);
    }
}
