package com.goranchik.movieland.client.utils.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class JsonJacksonConverter {
    private ObjectMapper objectMapper = new ObjectMapper();

    public String toJson(Object obj) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public  <T> T jsonToObj (String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (UnrecognizedPropertyException upe) {
           throw new RuntimeException("Incorrect format for json. Fields name should be one of these  "
                   + Arrays.asList(clazz.getFields()).stream().map(e -> e.getName()).collect(Collectors.toList()).toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
