package com.wolox.training.exceptions;

public enum ErrorDescription {

    BOOK_NOT_FOUND("Book not found.", 1);

    private String reason;
    private Integer code;

    private ErrorDescription(String reason, Integer code) {
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
