package com.wolox.training.exceptions;

public class OpenLibraryJsonParseException extends RuntimeException {

    private static final ErrorDescription errorDescription = ErrorDescription.OPEN_LIBRARY_JSON_PARSE;

    public ErrorDescription getErrorDescription() {
        return errorDescription;
    }
}
