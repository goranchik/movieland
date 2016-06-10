package com.goranchik.movieland.persistence.utils.generator.impl;

import com.goranchik.movieland.persistence.utils.generator.SQLGenerator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.IOException;
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
        try (Stream<String> stream = Files.lines(Paths.get(getTableResource(tableName).getURI()))) {
            stream.forEach(r -> resultSQL.append(r).append(END_LINE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultSQL.toString();
    }

    @Override
    public Resource getTableResource(String tableName){
        return new ClassPathResource(PATH_SCRIPT + tableName + SQL);
    }

    @Override
    public Resource getDataResource(String tableName){
        return new ClassPathResource(PATH_DATA + tableName + TXT);
    }
}
