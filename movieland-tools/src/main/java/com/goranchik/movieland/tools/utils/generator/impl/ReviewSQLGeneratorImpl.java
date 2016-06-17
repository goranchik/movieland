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
import static com.goranchik.movieland.tools.Table.*;

/**
 * Created by Ihor on 6/7/2016.
 */
@Service(REVIEW_SQL_GENERATOR)
public class ReviewSQLGeneratorImpl extends AbstractSQLGenerator implements SQLGenerator {

    private List<String> reviews = new ArrayList<>();
    private StringBuilder resultSQL = new StringBuilder();

    @Override
    public String getPopulateTableSQL(String tableName) {
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
            throw new RuntimeException(e);
        }

        resultSQL.append(getCreateTableSQL(tableName));
        reviews.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL, tableName,
                        Table.valueOf(tableName.toUpperCase()).dataFields(dbProperties), result)));

        return resultSQL.toString();
    }

}
