package com.wolox.training.exceptions;

public class BookAlreadyOwnedException extends RuntimeException {

    private static final ErrorDescription errorDescription = ErrorDescription.BOOK_ALREADY_OWNED;

    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }

}
