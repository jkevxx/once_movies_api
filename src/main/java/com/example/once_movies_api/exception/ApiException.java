package com.example.once_movies_api.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiException {

    private final String message;
    private final HttpStatus status;

    private final Long statusCode;

    private final List<String> errors;

    public ApiException(String message, HttpStatus status, Long statusCode, List<String> errors) {
        this.message = message;
        this.status = status;
        this.statusCode = statusCode;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Long getStatusCode() {
        return statusCode;
    }

    public List<String> getErrors() {
        return errors;
    }
}
