package com.goranchik.movieland.persistence.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ihor on 6/10/2016.
 */
@Service
public class PropertyDBService {
    public Properties getDBProperties() {
        Resource resource = new ClassPathResource("/config/db.properties");
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
