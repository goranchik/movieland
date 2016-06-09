package com.goranchik.movieland.client.utils.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class JsonJacksonConverter {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper = new ObjectMapper();

    public String toJson(Object obj) {
        String json = "";
        log.info("Start transform object {} to json ", obj);
        long startTime = System.currentTimeMillis();
        try {
            json = objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - startTime;
        log.info("Json {} is received. It took {} ms", json, time);
        return json;
    }

    private <T> T jsonToObj (String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
