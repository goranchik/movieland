package com.goranchik.movieland.persistence.utils.generator.impl;

import com.goranchik.movieland.persistence.utils.generator.SQLGenerator;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.stream.Stream;

import static com.goranchik.movieland.tools.Constants.*;

/**
 * Created by Ihor on 6/8/2016.
 */
public abstract class AbstractSQLGenerator implements SQLGenerator {

    @Override
    public abstract String getPopulateTableSQL(String tableName, Properties props);

    @Override
    public String getCreateTableSQL(String tableName) {
        StringBuilder resultSQL = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader()
                .getResource(PATH_SCRIPT + tableName + SQL)
                .toURI()))) {
            stream.forEach(r -> resultSQL.append(r).append("\n"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return resultSQL.toString();
    }
}
