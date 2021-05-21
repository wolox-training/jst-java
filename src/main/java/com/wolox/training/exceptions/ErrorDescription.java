package com.wolox.training.exceptions;

public enum ErrorDescription {

    BOOK_NOT_FOUND("Book not found.", 1);

    private final String reason;
    private final Integer code;

    ErrorDescription(String reason, Integer code) {
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
