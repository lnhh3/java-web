package com.learning.java_web.commons.responses;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public ResponseUtil(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public RestApiResponse<Object> createResponse(RestApiStatus restApiStatus, Object data) {
        return new RestApiResponse(restApiStatus, data);
    }

    public RestApiResponse<Object> createResponse(RestApiStatus restApiStatus, Object data, String description) {
        return new RestApiResponse(restApiStatus, data, description);
    }

    public ResponseEntity<RestApiResponse<Object>> buildResponse(RestApiStatus restApiStatus, Object data, HttpStatus status) {
        return new ResponseEntity(this.createResponse(restApiStatus, data), status);
    }

    public ResponseEntity<RestApiResponse<Object>> buildResponse(RestApiStatus restApiStatus, Object data, String description, HttpStatus status) {
        return new ResponseEntity(this.createResponse(restApiStatus, data, description), status);
    }

    public ResponseEntity<RestApiResponse<Object>> successResponse(Object data) {
        return this.buildResponse(RestApiStatus.OK, data, HttpStatus.OK);
    }

    public ResponseEntity<RestApiResponse<Object>> successResponse(Object data, String description) {
        return this.buildResponse(RestApiStatus.OK, data, description, HttpStatus.OK);
    }
}
