package com.wolox.training.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> resolveBookNotFoundException(BookNotFoundException exception) {
        String reason = exception.getErrorDescription().getReason();
        Integer code = exception.getErrorDescription().getCode();

        return new ResponseEntity<>(new ErrorResponse(reason, code), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookAlreadyOwnedException.class)
    public ResponseEntity<ErrorResponse> resolveBookAlreadyOwnedException(BookAlreadyOwnedException exception) {
        String reason = exception.getErrorDescription().getReason();
        Integer code = exception.getErrorDescription().getCode();

        return new ResponseEntity<>(new ErrorResponse(reason, code), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> resolveUserNotFoundException(UserNotFoundException exception) {
        String reason = exception.getErrorDescription().getReason();
        Integer code = exception.getErrorDescription().getCode();

        return new ResponseEntity<>(new ErrorResponse(reason, code), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationLoginException.class)
    public ResponseEntity<ErrorResponse> resolveValidationLoginException(ValidationLoginException exception) {
        String reason = exception.getErrorDescription().getReason();
        Integer code = exception.getErrorDescription().getCode();

        return new ResponseEntity<>(new ErrorResponse(reason, code), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OpenLibraryJsonParseException.class)
    public ResponseEntity<ErrorResponse> resolveOpenLibraryJsonParseException(OpenLibraryJsonParseException exception) {
        String reason = exception.getErrorDescription().getReason();
        Integer code = exception.getErrorDescription().getCode();

        return new ResponseEntity<>(new ErrorResponse(reason, code), HttpStatus.BAD_REQUEST);
    }
}
