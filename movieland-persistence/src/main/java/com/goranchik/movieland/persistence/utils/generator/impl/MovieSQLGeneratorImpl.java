package com.goranchik.movieland.persistence.utils.generator.impl;


import com.goranchik.movieland.persistence.utils.generator.SQLGenerator;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

import static com.goranchik.movieland.tools.Constants.*;
import static com.goranchik.movieland.persistence.utils.Table.*;

/**
 * Created by Ihor on 6/7/2016.
 */
@Service(SQLGenerator.MOVIE_SQL_GENERATOR)
public class MovieSQLGeneratorImpl extends AbstractSQLGenerator implements SQLGenerator {
    private List<String> movies = new ArrayList<>();
    private Set<String> countries = new HashSet<>();
    private Map<String, List<String>> movie_country = new HashMap<>();
    private Map<String, List<String>> movie_genre = new HashMap<>();
    private StringBuilder resultSQL = new StringBuilder();

    @Override
    public String getPopulateTableSQL(String tableName, Properties props) {
        String fields = props.getProperty(MOVIE.fields());
        try (Stream<String> stream = Files.lines(Paths.get(getClass().getClassLoader()
                .getResource(PATH_DATA + tableName + TXT)
                .toURI()))) {
            StringBuilder temp = new StringBuilder();
            final int[] i = {0};
            final String[] names = new String[2];
            stream.forEach(
                    result -> {
                        i[0]++;
                        if (!result.isEmpty()) {
                            switch (i[0]) {
                                case 1:
                                    names[0] = result.split(MOIVE_DELIMITER)[0];
                                    names[1] = result.split(MOIVE_DELIMITER)[1];
                                    temp.append(SQL_WRAPPER).append(names[0]).append(SQL_WRAPPER)
                                        .append(SQL_DELIMITER)
                                        .append(SQL_WRAPPER).append(names[1].replace("'", "''"))
                                        .append(SQL_WRAPPER).append(SQL_DELIMITER);
                                    break;
                                case 2:
                                    temp.append(result).append(SQL_DELIMITER);
                                    break;
                                case 3:
                                    List<String> tmpCountries = Arrays.asList(result.split(ENTITY_DELIMITER));
                                    countries.addAll(tmpCountries);
                                    movie_country.put(names[0], tmpCountries);
                                    break;
                                case 4:
                                    movie_genre.put(names[0], Arrays.asList(result.split(ENTITY_DELIMITER)));
                                    break;
                                case 5:
                                    temp.append(SQL_WRAPPER).append(result)
                                        .append(SQL_WRAPPER).append(SQL_DELIMITER);
                                    break;
                                case 6:
                                    temp.append(result.substring(result.indexOf(DECIMAL_DELIMITER) + 1))
                                        .append(SQL_DELIMITER);
                                    break;
                                case 7:
                                    temp.append(result.substring(result.indexOf(DECIMAL_DELIMITER) + 1));
                                    break;
                                default:
                                    temp.append(SQL_WRAPPER).append(result).append(SQL_WRAPPER);
                            }
                        } else {
                            movies.add(temp.toString());
                            temp.delete(0, temp.length());
                            i[0] = 0;
                        }
                    }
            );
            if (temp.length()!=0) {
                movies.add(temp.toString());
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        resultSQL.append(getCreateTableSQL(MOVIE.name().toLowerCase()));
        movies.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL, tableName, fields, result)));

        resultSQL.append(getCreateTableSQL(COUNTRY.name().toLowerCase()));
        countries.stream().forEach(result ->
                resultSQL.append(String.format(INSERT_SQL,
                        COUNTRY.name(),
                        props.getProperty(COUNTRY.fields()), SQL_WRAPPER + result + SQL_WRAPPER)));

        resultSQL.append(getCreateTableSQL(MOVIE_COUNTRY.name().toLowerCase()));
        movie_country.entrySet().stream().forEach(movie ->
            {
                movie.getValue().stream().forEach(country ->
                    resultSQL.append(String.format(INSERT_SQL,
                            MOVIE_COUNTRY.name(),
                            props.getProperty(MOVIE_COUNTRY.fields()),
                            String.format(GET_ID_BY_NAME_SQL, MOVIE.name(), movie.getKey()) + SQL_DELIMITER +
                                    String.format(GET_ID_BY_NAME_SQL, COUNTRY.name(), country)
                        )
                    ));
            }
        );

        resultSQL.append(getCreateTableSQL(MOVIE_GENRE.name().toLowerCase()));
        movie_genre.entrySet().stream().forEach(movie ->
            {
                movie.getValue().stream().forEach(genre ->
                    resultSQL.append(String.format(INSERT_SQL,
                            MOVIE_GENRE.name(),
                            props.getProperty(MOVIE_GENRE.fields()),
                            String.format(GET_ID_BY_NAME_SQL, MOVIE.name(), movie.getKey()) + SQL_DELIMITER +
                                    String.format(GET_ID_BY_NAME_SQL, GENRE.name(), genre)
                        )
                    ));
            }
        );

        return resultSQL.toString();
    }

}
