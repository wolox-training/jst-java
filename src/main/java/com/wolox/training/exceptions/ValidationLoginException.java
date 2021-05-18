package com.wolox.training.exceptions;

public class ValidationLoginException extends RuntimeException {

    private static final ErrorDescription errorDescription = ErrorDescription.VALIDATION_LOGIN;

    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

}
