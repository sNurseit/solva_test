package com.example.test_solva.model.mapper;

import com.example.test_solva.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.example.test_solva.model.constant.ExceptionTextConstants.CONVERTING_JSON_TO_MAP_ERROR;
import static com.example.test_solva.model.constant.ExceptionTextConstants.CONVERTING_MAP_TO_JSON_ERROR;

public class MapToStringConverter implements AttributeConverter<Map<String, Object>, String> {

    @Override
    public String convertToDatabaseColumn(Map<String, Object> stringObjectMap) {
        if (stringObjectMap == null){
            return "{}";
        }
        try {
            return ObjectMapperUtil.attributeConverterObjectMapper.writeValueAsString(stringObjectMap);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(CONVERTING_MAP_TO_JSON_ERROR, e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String jsonString) {
        if (jsonString == null || jsonString.isEmpty()) {
            return new HashMap<>();
        }
        try {
            return ObjectMapperUtil.attributeConverterObjectMapper.readValue(jsonString, HashMap.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(CONVERTING_JSON_TO_MAP_ERROR, e);
        }
    }
}
