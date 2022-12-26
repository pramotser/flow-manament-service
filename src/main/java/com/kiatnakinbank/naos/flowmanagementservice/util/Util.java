package com.kiatnakinbank.naos.flowmanagementservice.util;

import com.kiatnakinbank.naos.flowmanagementservice.constants.Constants;
import com.kiatnakinbank.naos.flowmanagementservice.dto.base.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class Util {

    public static ResponseEntity<Response> createResponse(int responseResponse, Object responseObject) {
        HttpStatus httpStatus = HttpStatus.OK;
        String responseDescription = "";
        if (responseResponse == Constants.ResponseCode.OK) {
            responseDescription = Constants.ResponseDescription.OK;
        } else if (responseResponse == Constants.ResponseCode.CREATED) {
            httpStatus = HttpStatus.CREATED;
            responseDescription = Constants.ResponseDescription.CREATED;
        } else if (responseResponse == Constants.ResponseCode.ACCEPTED) {
            httpStatus = HttpStatus.ACCEPTED;
            responseDescription = Constants.ResponseDescription.ACCEPTED;
        } else if (responseResponse == Constants.ResponseCode.NO_CONTENT) {
            httpStatus = HttpStatus.NO_CONTENT;
            responseDescription = Constants.ResponseDescription.NO_CONTENT;
        } else if (responseResponse == Constants.ResponseCode.NOT_MODIFIED) {
            httpStatus = HttpStatus.NOT_MODIFIED;
            responseDescription = Constants.ResponseDescription.NOT_MODIFIED;
        } else if (responseResponse == Constants.ResponseCode.BAD_REQUEST) {
            httpStatus = HttpStatus.BAD_REQUEST;
            responseDescription = Constants.ResponseDescription.BAD_REQUEST;
        } else if (responseResponse == Constants.ResponseCode.UNAUTHORIZED) {
            httpStatus = HttpStatus.UNAUTHORIZED;
            responseDescription = Constants.ResponseDescription.UNAUTHORIZED;
        } else if (responseResponse == Constants.ResponseCode.FORBIDDEN) {
            httpStatus = HttpStatus.FORBIDDEN;
            responseDescription = Constants.ResponseDescription.FORBIDDEN;
        } else if (responseResponse == Constants.ResponseCode.PAYLOAD_TOO_LARGE) {
            httpStatus = HttpStatus.PAYLOAD_TOO_LARGE;
            responseDescription = Constants.ResponseDescription.PAYLOAD_TOO_LARGE;
        } else if (responseResponse == Constants.ResponseCode.UNPROCESSABLE_ENTITY) {
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            responseDescription = Constants.ResponseDescription.UNPROCESSABLE_ENTITY;
        } else if (responseResponse == Constants.ResponseCode.INTERNAL_SERVER_ERROR) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            responseDescription = Constants.ResponseDescription.INTERNAL_SERVER_ERROR;
        } else if (responseResponse == Constants.ResponseCode.SERVICE_UNAVAILABLE) {
            httpStatus = HttpStatus.SERVICE_UNAVAILABLE;
            responseDescription = Constants.ResponseDescription.SERVICE_UNAVAILABLE;
        } else if( responseResponse == Constants.ResponseCode.CONFLICT){
            httpStatus = HttpStatus.CONFLICT;
            responseDescription = Constants.ResponseDescription.CONFLICT;
        }
        Response response = new Response(responseResponse, responseDescription, responseObject);
        return ResponseEntity.status(httpStatus).body(response);
    }
}
