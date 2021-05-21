package com.wolox.training.exceptions;

public enum ErrorDescription {

    BOOK_NOT_FOUND("Book not found.", 1), BOOK_ALREADY_OWNED("Book already owned", 2),
    USER_NOT_FOUND("User not found", 3);

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
