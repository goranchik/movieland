package com.goranchik.movieland.tools;

/**
 * Created by Ihor on 6/8/2016.
 */
public final class Constants {
    public static final String TXT = ".txt";
    public static final String SQL = ".sql";
    public static final String SQL_WRAPPER = "'";
    public static final String SINGLE_QUOTE = "'";
    public static final String DOUBLE_QUOTE = "''";
    public static final String END_LINE = "\n";
    public static final String SQL_DELIMITER = ", ";
    public static final String ENTITY_DELIMITER = ", ";
    public static final String MOIVE_DELIMITER = "/";
    public static final String DECIMAL_DELIMITER = ":";
    public static final String INSERT_SQL = "INSERT INTO %s (%s) VALUES (%s);\n";
    public static final String GET_ID_BY_NAME_SQL = "(SELECT id FROM %s WHERE name = '%s')";
    public static final String FIELD_ID = "id";
}
