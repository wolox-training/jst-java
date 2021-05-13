package com.wolox.training.exceptions;

public class BookNotFoundException extends RuntimeException {

    private final ErrorDescription errorDescription = ErrorDescription.BOOK_NOT_FOUND;

    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }
}
