package com.wolox.training.exceptions;

public class ErrorResponse {

    private final String reason;
    private final Integer code;

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
