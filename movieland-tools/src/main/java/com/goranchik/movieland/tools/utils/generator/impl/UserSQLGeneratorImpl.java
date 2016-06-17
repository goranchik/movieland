package com.goranchik.movieland.tools.utils.generator.impl;


import com.goranchik.movieland.tools.Table;
import com.goranchik.movieland.tools.utils.generator.SQLGenerator;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.goranchik.movieland.tools.Constants.*;

/**
 * Created by Ihor on 6/7/2016.
 */
@Service(USER_SQL_GENERATOR)
public class UserSQLGeneratorImpl extends AbstractSQLGenerator implements SQLGenerator {

    private List<String> users = new ArrayList<>();
    private StringBuilder resultSQL = new StringBuilder();

    @Override
    public String getPopulateTableSQL(String tableName) {
        try (Stream<String> stream = Files.lines(Paths.get(getDataResource(tableName).getURI()))) {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resultSQL.append(getCreateTableSQL(tableName));
        users.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL, tableName,
                        Table.valueOf(tableName.toUpperCase()).dataFields(dbProperties), result)));

        return resultSQL.toString();
    }
}