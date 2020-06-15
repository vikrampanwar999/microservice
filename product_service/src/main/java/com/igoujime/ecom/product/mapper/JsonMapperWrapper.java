package com.igoujime.ecom.product.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igoujime.ecom.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JsonMapperWrapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonMapperWrapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> String writeValue(T obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }

    public List<Product> readProductValue(String json) {

        try {
            return Arrays.asList(objectMapper.readValue(json, Product[].class));

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
