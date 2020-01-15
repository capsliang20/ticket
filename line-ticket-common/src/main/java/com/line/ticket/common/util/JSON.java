package com.line.ticket.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JSON {
    private static final ObjectMapper objectMapper=new ObjectMapper();
    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

    public static String toJSONString(Object value){
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "JsonProcessingException";
    }

    public static ObjectMapper objectMapper(){
        return objectMapper;
    }
}
