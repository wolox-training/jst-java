package com.wolox.training.exceptions;

public class ErrorResponse {

    private String reason;
    private Integer code;

    public ErrorResponse(String reason, Integer code){
        this.reason = reason;
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public Integer getCode() {
        return code;
    }
}
