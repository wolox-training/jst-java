package com.wolox.training.exceptions;

public class BookNotFoundException extends RuntimeException {

    private static final ErrorDescription errorDescription = ErrorDescription.BOOK_NOT_FOUND;

    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }
}
