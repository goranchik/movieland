package com.goranchik.movieland.tools.utils.generator.impl;

import com.goranchik.movieland.tools.utils.generator.SqlLaunchGeneratorService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.goranchik.movieland.tools.Constants.*;
import static com.goranchik.movieland.tools.enums.Table.GENRE;

/**
 * Created by Ihor on 6/7/2016.
 */
@Service(GENRE_SQL_LAUNCH_GENERATOR)
public class GenreSqlLaunchGeneratorService extends AbstractSqlLaunchGeneratorService implements SqlLaunchGeneratorService {

    private List<String> genres = new ArrayList<>();
    private StringBuilder resultSQL = new StringBuilder();

    @Override
    public String getPopulateTableSql(String tableName) {
        try (Stream<String> stream = Files.lines(Paths.get(getDataResource(tableName).getURI()))) {
            genres = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        resultSQL.append(getCreateTableSql(tableName));
        genres.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL, tableName, GENRE.getDataFields(),
                        SQL_WRAPPER + result + SQL_WRAPPER)));
        return resultSQL.toString();
    }

}
