package com.goranchik.movieland.persistence.utils.generator.impl;

import com.goranchik.movieland.persistence.utils.Table;
import com.goranchik.movieland.persistence.utils.generator.SQLGenerator;
import org.springframework.stereotype.Service;
import java.io.IOException;
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
@Service(SQLGenerator.REVIEW_SQL_GENERATOR)
public class ReviewSQLGeneratorImpl extends AbstractSQLGenerator implements SQLGenerator {
    private List<String> reviews = new ArrayList<>();
    private StringBuilder resultSQL = new StringBuilder();

    @Override
    public String getPopulateTableSQL(String tableName, Properties props) {
        String fields = props.getProperty(Table.valueOf(tableName.toUpperCase()).fields());
        try (Stream<String> stream = Files.lines(Paths.get(getDataResource(tableName).getURI()))) {
            StringBuilder temp = new StringBuilder();
            final int[] i = {0};
            stream.forEach(
                    result -> {
                        i[0]++;
                        if (!result.isEmpty()) {
                            switch (i[0]) {
                                case 1:
                                    temp.append(String.format(GET_ID_BY_NAME_SQL, MOVIE.name(), result))
                                        .append(SQL_DELIMITER);
                                    break;
                                case 2:
                                    temp.append(String.format(GET_ID_BY_NAME_SQL, USERS.name(), result))
                                        .append(SQL_DELIMITER);
                                    break;
                                default:
                                    temp.append(SQL_WRAPPER).append(result).append(SQL_WRAPPER);
                            }
                        } else {
                            reviews.add(temp.toString());
                            temp.delete(0, temp.length());
                            i[0] = 0;
                        }
                    }
            );
            if (temp.length()!=0) {
                reviews.add(temp.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        resultSQL.append(getCreateTableSQL(tableName));
        reviews.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL, tableName, fields, result)));

        return resultSQL.toString();
    }

}
