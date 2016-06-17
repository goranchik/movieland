package com.goranchik.movieland.tools.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Ihor on 6/10/2016.
 */

public class PropTools {
    public static Properties getDBProperties() {
        Resource resource = new ClassPathResource("/config/db.properties");
        try {
            return PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
