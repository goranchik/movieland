package com.goranchik.movieland.tools;

/**
 * Created by Ihor on 6/8/2016.
 */
public final class Constants {
    public static final String TXT = ".txt";
    public static final String SQL_WRAPPER = "'";
    public static final String SINGLE_QUOTE = "'";
    public static final String DOUBLE_QUOTE = "''";
    public static final String END_LINE = "\n";
    public static final String SQL_DELIMITER = ", ";
    public static final String ENTITY_DELIMITER = ", ";
    public static final String MOVIE_DELIMITER = "/";
    public static final String RATING_PRICE_DELIMITER = ":";
    public static final String PROP_TABLE_FIELDS = ".table.fields";
    public static final String PROP_DATA_FIELDS = ".data.fields";
    public static final String CACHE_EVICT_CRON = ".cache.evict.cron";

    public static final String GENRE_SQL_LAUNCH_GENERATOR = "genreSqlLaunchGenerator";
    public static final String USER_SQL_LAUNCH_GENERATOR = "userSqlLaunchGenerator";
    public static final String MOVIE_SQL_LAUNCH_GENERATOR = "movieSqlLaunchGenerator";
    public static final String REVIEW_SQL_LAUNCH_GENERATOR = "reviewSqlLaunchGenerator";

    public static final String INSERT_SQL = "INSERT INTO %s (%s) VALUES (%s);\n";
    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS %s CASCADE;\n";
    public static final String CREATE_TABLE_SQL = "CREATE TABLE %s\n(%s);\n";
    public static final String GET_ID_BY_NAME_SQL = "(SELECT id FROM %s WHERE name = '%s')";
    public static final String WHERE = " WHERE ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String AND = " AND ";
    public static final String LIKE_PERCENT = "%";
    public static final String SPACE = " ";
    public static final String FIELD_ID = "id";

    public static final String CACHE_PRINCIPAL = "principal";

    public static final String COOKIE_TOKEN = "auth-token";
    public static final String COOKIE_PATH = "/v1/";

    public static final Long REVIEW_LIMIT = 2L;

}
