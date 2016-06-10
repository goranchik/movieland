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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.goranchik.movieland.tools.Constants.*;

/**
 * Created by Ihor on 6/7/2016.
 */
@Service(SQLGenerator.GENRE_SQL_GENERATOR)
public class GenreSQLGeneratorImpl extends AbstractSQLGenerator implements SQLGenerator {

    private List<String> genres = new ArrayList<>();
    private StringBuilder resultSQL = new StringBuilder();

    @Override
    public String getPopulateTableSQL(String tableName, Properties props) {
        String fields = props.getProperty(Table.valueOf(tableName.toUpperCase()).fields());
        try (Stream<String> stream = Files.lines(Paths.get(getDataResource(tableName).getURI()))) {
            genres = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultSQL.append(getCreateTableSQL(tableName));
        genres.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL, tableName, fields,
                        SQL_WRAPPER + result + SQL_WRAPPER)));
        return resultSQL.toString();
    }

}
