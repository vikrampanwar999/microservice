package com.igoujime.ecom.cart.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.igoujime.ecom.cart.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    public Cart readCartValue(String json) {

        try {
            return objectMapper.readValue(json, Cart.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
