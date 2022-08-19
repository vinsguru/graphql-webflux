package com.vinsguru.graphqlplayground.lec15.exceptions;

import org.springframework.graphql.execution.ErrorType;

import java.util.Map;

public class ApplicationException extends RuntimeException{

    private final ErrorType errorType;
    private final String message;
    private final Map<String, Object> extensions;

    public ApplicationException(ErrorType errorType, String message, Map<String, Object> extensions) {
        super(message);
        this.errorType = errorType;
        this.message = message;
        this.extensions = extensions;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Map<String, Object> getExtensions() {
        return extensions;
    }
}
