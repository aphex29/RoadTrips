package com.pmar.roadtrip.FormatData;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class StringToRequestObject {
    public static RequestObject mapToObject(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json,RequestObject.class);
        }
        catch(Exception e){
            System.err.println(e);
            return new RequestObject();
        }
    }
}
