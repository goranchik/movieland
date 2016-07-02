package com.goranchik.movieland.tools.enums;

import com.goranchik.movieland.tools.utils.PropTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static com.goranchik.movieland.tools.Constants.*;

/**
 * Created by Ihor on 6/7/2016.
 */
@Getter
@NoArgsConstructor
public enum Table {
    GENRE("genre"),
    USERS("users"),
    MOVIE("movie"),
    COUNTRY("country"),
    MOVIE_COUNTRY("movie_country"),
    MOVIE_GENRE("movie_genre"),
    REVIEW("review");

    private String name;
    private String dataFields;
    private String tableFields;
    private List<String> fieldSet;
    private String cacheEvictCron;
    private Properties dbProps = PropTools.getDBProperties();

    Table(String name){
        this.name = name;
        dataFields = dbProps.getProperty(name + PROP_DATA_FIELDS);
        tableFields = dbProps.getProperty(name + PROP_TABLE_FIELDS);
        fieldSet = Arrays.asList(dbProps.getProperty(name + PROP_DATA_FIELDS).split(ENTITY_DELIMITER));
        cacheEvictCron = dbProps.getProperty(name + CACHE_EVICT_CRON);
    }


}
