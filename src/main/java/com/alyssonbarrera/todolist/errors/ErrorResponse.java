package com.alyssonbarrera.todolist.errors;

public class ErrorResponse {
    private String message;
    private Number statusCode;

    public ErrorResponse(String message, Number statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Number getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Number statusCode) {
        this.statusCode = statusCode;
    }
}