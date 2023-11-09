package com.learning.java_web.commons.exceptions;

import com.learning.java_web.commons.responses.RestApiMessage;
import com.learning.java_web.commons.responses.RestApiStatus;

public class ApplicationException extends RuntimeException{
    private RestApiStatus restApiStatus;
    private Object data;

    public ApplicationException(RestApiStatus restApiStatus) {
        super(restApiStatus.getMessage());
        this.restApiStatus = restApiStatus;
    }

    public ApplicationException(RestApiStatus restApiStatus, Object data) {
        this.restApiStatus = restApiStatus;
        this.data = data;
    }

    public ApplicationException(RestApiStatus restApiStatus, String message) {
        super(message);
        this.restApiStatus = restApiStatus;
    }

    public ApplicationException(RestApiStatus restApiStatus, RestApiMessage message) {
        super(message.toString());
        this.restApiStatus = restApiStatus;
    }

    public ApplicationException(RestApiStatus restApiStatus, Object data, String message) {
        super(message);
        this.restApiStatus = restApiStatus;
        this.data = data;
    }

    public ApplicationException(Throwable throwable) {
        super(throwable);
    }

    public RestApiStatus getRestApiStatus() {
        return this.restApiStatus;
    }

    public Object getData() {
        return this.data;
    }
}
