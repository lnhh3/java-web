package com.learning.java_web.commons.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiResponse<T extends Object> implements Serializable {
    private int status;
    private String message;
    private T data;
    private String description;

    public RestApiResponse(RestApiStatus restApiStatus, T data) {
        this.status = restApiStatus.getStatus();
        this.message = restApiStatus.getMessage();
        this.data = data;
        this.description = "";
    }

    public RestApiResponse(RestApiStatus restApiStatus, T data, String description) {
        this.status = restApiStatus.getStatus();
        this.message = restApiStatus.getMessage();
        this.data = data;
        this.description = description;
    }
}
