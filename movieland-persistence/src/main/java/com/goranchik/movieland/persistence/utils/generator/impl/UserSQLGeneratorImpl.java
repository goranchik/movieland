package com.goranchik.movieland.persistence.utils.generator.impl;


import com.goranchik.movieland.persistence.utils.generator.SQLGenerator;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import static com.goranchik.movieland.tools.Constants.*;
import static com.goranchik.movieland.persistence.utils.Table.*;

/**
 * Created by Ihor on 6/7/2016.
 */
@Service(SQLGenerator.USER_SQL_GENERATOR)
public class UserSQLGeneratorImpl extends AbstractSQLGenerator implements SQLGenerator {
    private List<String> users = new ArrayList<>();
    private StringBuilder resultSQL = new StringBuilder();

    @Override
    public String getPopulateTableSQL(String tableName, Properties props) {
        String fields = props.getProperty(USERS.fields());
        try (Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader()
                .getResource(PATH_DATA + tableName + TXT)
                .toURI()))) {
            StringBuilder temp = new StringBuilder();
            stream.forEach(
                    result -> {
                        if (!result.isEmpty()) {
                            temp.append(SQL_WRAPPER).append(result)
                                .append(SQL_WRAPPER).append(SQL_DELIMITER);
                        } else {
                            temp.delete(temp.length() - 2, temp.length());
                            users.add(temp.toString());
                            temp.delete(0, temp.length());
                        }
                    }
            );
            if (temp.length()!=0) {
                temp.delete(temp.length() - 2, temp.length());
                users.add(temp.toString());
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        resultSQL.append(getCreateTableSQL(USERS.name().toLowerCase()));
        users.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL, tableName, fields, result)));

        return resultSQL.toString();
    }
}
