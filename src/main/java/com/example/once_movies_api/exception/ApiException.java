package com.example.once_movies_api.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ApiException {

    private final String message;
    private final Long status;

    private final List<String> errors;

    public ApiException(String message, Long status, List<String> errors) {
        this.message = message;
        this.status = status;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public Long getStatus() {
        return status;
    }

    public List<String> getErrors() {
        return errors;
    }
}
