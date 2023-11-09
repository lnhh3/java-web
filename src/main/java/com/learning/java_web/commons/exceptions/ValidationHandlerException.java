package com.learning.java_web.commons.exceptions;

import com.learning.java_web.commons.responses.ResponseUtil;
import com.learning.java_web.commons.responses.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ValidationHandlerException {
    @Autowired
    ResponseUtil responseUtil;

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<RestApiResponse<Object>> applicationHandlerException(ApplicationException appException) {
        return this.responseUtil.buildResponse(appException.getRestApiStatus(), appException.getMessage(), HttpStatus.OK);
    }
}
