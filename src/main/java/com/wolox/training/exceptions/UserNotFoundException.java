package com.wolox.training.exceptions;

public class UserNotFoundException extends RuntimeException {

    private static final ErrorDescription errorDescription = ErrorDescription.USER_NOT_FOUND;

    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

}
