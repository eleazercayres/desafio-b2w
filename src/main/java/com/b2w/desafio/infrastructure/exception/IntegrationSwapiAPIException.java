package com.b2w.desafio.infrastructure.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class IntegrationSwapiAPIException extends RuntimeException {
    @Getter
    private transient Map content;

    @Getter
    private HttpStatus httpStatus;

    public IntegrationSwapiAPIException() {
        super();
    }

    public IntegrationSwapiAPIException(String message, HttpStatus httpStatus) {
        this(message, httpStatus, null);
    }

    public IntegrationSwapiAPIException(String message, HttpStatus httpStatus, Map content) {
        super(message);
        this.httpStatus = httpStatus;
        this.content = content;
    }

    public abstract IntegrationType getIntegrationType();
}
