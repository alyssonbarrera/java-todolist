package com.alyssonbarrera.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMostSpecificCause().getMessage(), 400);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(AppError.class)
    public ResponseEntity<ErrorResponse> handleAppError(AppError ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getStatusCode());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }
}
