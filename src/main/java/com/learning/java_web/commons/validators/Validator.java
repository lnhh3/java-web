package com.learning.java_web.commons.validators;

import com.learning.java_web.commons.exceptions.ApplicationException;
import com.learning.java_web.commons.responses.RestApiMessage;
import com.learning.java_web.commons.responses.RestApiStatus;

public class Validator {
    public static boolean isNull(Object data) {
        return data == null;
    }

    public static boolean isEmpty(String data) {
        return data == "";
    }

    public static void notEmpty(String data, RestApiStatus restApiStatus, RestApiMessage restApiMessage) {
        if (data == "") {
            throw new ApplicationException(restApiStatus, restApiMessage);
        }
    }

    public static void notNull(Object data, RestApiStatus restApiStatus, RestApiMessage restApiMessage) {
        if (data == null) {
            throw new ApplicationException(restApiStatus, restApiMessage);
        }
    }

    public static void notNullAndNotEmpty(String data, RestApiStatus restApiStatus, RestApiMessage restApiMessage) {
        if (data == null || data == "") {
            throw new ApplicationException(restApiStatus, restApiMessage);
        }
    }

    public static void mustNull(Object data, RestApiStatus restApiStatus, RestApiMessage restApiMessage) {
        if (data != null) {
            throw new ApplicationException(restApiStatus, restApiMessage);
        }
    }

    public static void notExisted(boolean data, RestApiStatus restApiStatus, RestApiMessage restApiMessage) {
        if (data) {
            throw new ApplicationException(restApiStatus, restApiMessage);
        }
    }
}
