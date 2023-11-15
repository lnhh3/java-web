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

    public static void mustEqualString(String obj1, String obj2, RestApiStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(!obj1.equals(obj2)) {
            throw new ApplicationException(restAPIStatus, restApiMessage);
        }
    }

    public static void mustTrue(boolean data, RestApiStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(!data) {
            throw new ApplicationException(restAPIStatus, restApiMessage);
        }
    }

    public static void mustGreater(int num, int intCompare, RestApiStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(num <= intCompare) throw new ApplicationException(restAPIStatus, restApiMessage);
    }

    public static void mustLessThan(int num, int intCompare, RestApiStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(num >= intCompare) throw new ApplicationException(restAPIStatus, restApiMessage);
    }

    public static void mustEqual(int num1, int num2, RestApiStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(num1 != num2) throw new ApplicationException(restAPIStatus, restApiMessage);
    }
}
