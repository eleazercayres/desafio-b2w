package com.b2w.desafio.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class IntegrationException extends RuntimeException {

    @Getter
    private final transient Map content;

    @Getter
    private final HttpStatus httpStatus;

    IntegrationException(String message, HttpStatus httpStatus) {
        this(message, httpStatus, null);
    }

    private IntegrationException(String message, HttpStatus httpStatus, Map content) {
        super(message);
        this.httpStatus = httpStatus;
        this.content = content;
    }

}