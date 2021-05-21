package com.wolox.training.utils;

public enum GuavaPreConditionsMessage {
    CHECK_NOT_NULL("Please check the %s supplied, its illegal!");

    private final String message;

    GuavaPreConditionsMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
