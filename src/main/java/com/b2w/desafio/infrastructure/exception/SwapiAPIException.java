package com.b2w.desafio.infrastructure.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

public class SwapiAPIException extends IntegrationSwapiAPIException {

	public SwapiAPIException() {
        super();
    }

    public SwapiAPIException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public SwapiAPIException(String message, HttpStatus httpStatus, Map content) {
        super(message, httpStatus, content);
    }

    @Override
    public IntegrationType getIntegrationType() {
        return IntegrationType.SWAPI;
    }

}
