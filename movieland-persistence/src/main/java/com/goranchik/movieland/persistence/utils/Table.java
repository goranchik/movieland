package com.goranchik.movieland.persistence.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.goranchik.movieland.tools.Constants.ENTITY_DELIMITER;

/**
 * Created by Ihor on 6/7/2016.
 */
public enum Table {
    GENRE,
    USERS,
    MOVIE,
    COUNTRY,
    MOVIE_COUNTRY,
    MOVIE_GENRE,
    REVIEW;

    private final static String FIELDS = ".fields";

    public String fields(Properties properties) {
        return properties.getProperty(nameLowerCase() + FIELDS);
    }

    public List<String> fieldSet(Properties properties) {
        return Arrays.asList(properties.getProperty(nameLowerCase() + FIELDS).split(ENTITY_DELIMITER));
    }

    public String nameLowerCase() {
        return name().toLowerCase();
    }

}
