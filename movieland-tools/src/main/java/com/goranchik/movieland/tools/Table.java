package com.goranchik.movieland.tools;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.goranchik.movieland.tools.Constants.*;

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

    public String dataFields(Properties properties) {
        return properties.getProperty(nameLowerCase() + PROP_DATA_FIELDS);
    }

    public String tableFields(Properties properties) {
        return properties.getProperty(nameLowerCase() + PROP_TABLE_FIELDS);
    }

    public List<String> fieldSet(Properties properties) {
        return Arrays.asList(properties.getProperty(nameLowerCase() + PROP_DATA_FIELDS).split(ENTITY_DELIMITER));
    }

    public String nameLowerCase() {
        return name().toLowerCase();
    }

}
