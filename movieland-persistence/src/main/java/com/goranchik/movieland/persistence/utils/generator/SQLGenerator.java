package com.goranchik.movieland.persistence.utils.generator;

import org.springframework.core.io.Resource;
import java.util.Properties;

/**
 * Created by Ihor on 6/7/2016.
 */
public interface SQLGenerator {
    String GENRE_SQL_GENERATOR = "genreSQLGenerator";
    String USER_SQL_GENERATOR = "userSQLGenerator";
    String MOVIE_SQL_GENERATOR = "movieSQLGenerator";
    String REVIEW_SQL_GENERATOR = "reviewSQLGenerator";
    String getPopulateTableSQL(String tableName, Properties props);
    String getCreateTableSQL(String tableName);
    Resource getTableResource(String tableName);
    Resource getDataResource(String tableName);
}
