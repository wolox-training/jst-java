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

}
