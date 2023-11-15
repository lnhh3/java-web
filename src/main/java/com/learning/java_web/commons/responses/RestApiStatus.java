package com.learning.java_web.commons.responses;

public enum RestApiStatus {
        OK(200, "OK"),
        NOT_FOUND(404, "NOT FOUND"),
        BAD_REQUEST(400, "BAD REQUEST"),
        EXISTED(405, "ALREADY EXISTED"),
        CONFLICT(409, "CONFLICT"),
        BAD_REQUEST_PARAM(406, "INVALID DATA"),
    ;

    private final int status;
    private final String message;

    private RestApiStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public static RestApiStatus getEnum(int status) {
        for (RestApiStatus restApiStatus : values())
            if (restApiStatus.getStatus() == status) return restApiStatus;
        throw new IllegalArgumentException();
    }
}
