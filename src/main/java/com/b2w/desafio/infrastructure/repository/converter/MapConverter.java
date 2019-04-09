package com.b2w.desafio.infrastructure.repository.converter;

import com.b2w.desafio.infrastructure.exception.MapConverterException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Converter
public class MapConverter implements AttributeConverter<Map<String, Object>, String> {

    private static final ObjectMapper OM = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return OM.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new MapConverterException(e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return dbData != null ? OM.readValue(dbData, HashMap.class) : null;
        } catch (IOException e) {
            throw new MapConverterException(e);
        }
    }
}
