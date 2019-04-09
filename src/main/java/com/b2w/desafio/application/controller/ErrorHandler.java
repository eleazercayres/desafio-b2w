package com.b2w.desafio.application.controller;

import static org.springframework.http.ResponseEntity.status;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.b2w.desafio.infrastructure.exception.RemovePLanetException;
import com.b2w.desafio.infrastructure.exception.SavedPlanetException;
import com.b2w.desafio.infrastructure.exception.SwapiAPIException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@AllArgsConstructor
public class ErrorHandler {

    @ExceptionHandler(RemovePLanetException.class)
    public ResponseEntity removePLanetException(RemovePLanetException ex) {
        log.error(ex.getMessage(), ex);
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorMap("Erro delete Planet"));
    }
    
    @ExceptionHandler(SwapiAPIException.class)
    public ResponseEntity swapiAPIException(SwapiAPIException ex) {
        log.error(ex.getMessage(), ex);
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorMap("Erro consult swapi Planet"));
    }
    
    @ExceptionHandler(SavedPlanetException.class)
    public ResponseEntity swapiAPIException(SavedPlanetException ex) {
        log.error(ex.getMessage(), ex);
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorMap("Erro save Planet"));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity unknownError(Exception ex) {
        log.error(ex.getMessage(), ex);
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(createErrorMap(ex.getMessage()));
    }

    private static Map createErrorMap(String message) {
        Map map = new HashMap();
        map.put("error", message);
        return map;
    }
}
