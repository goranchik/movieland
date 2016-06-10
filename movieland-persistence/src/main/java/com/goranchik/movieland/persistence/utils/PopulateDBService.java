package com.goranchik.movieland.persistence.utils;

import com.goranchik.movieland.persistence.utils.generator.SQLGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.goranchik.movieland.persistence.utils.Table.*;

/**
 * Created by Ihor on 6/8/2016.
 */
@Service
public class PopulateDBService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(SQLGenerator.GENRE_SQL_GENERATOR)
    private SQLGenerator genreSQLGenereator;

    @Autowired
    @Qualifier(SQLGenerator.USER_SQL_GENERATOR)
    private SQLGenerator userSQLGenereator;

    @Autowired
    @Qualifier(SQLGenerator.MOVIE_SQL_GENERATOR)
    private SQLGenerator movieSQLGenereator;

    @Autowired
    @Qualifier(SQLGenerator.REVIEW_SQL_GENERATOR)
    private SQLGenerator reviewSQLGenereator;

    private void runSQL(String sql) {
        jdbcTemplate.execute(sql);
    }

    @PostConstruct
    public void initDBLoad() throws IOException {
        runSQL(genreSQLGenereator.getPopulateTableSQL(GENRE.nameLowerCase()));
        runSQL(userSQLGenereator.getPopulateTableSQL(USERS.nameLowerCase()));
        runSQL(movieSQLGenereator.getPopulateTableSQL(MOVIE.nameLowerCase()));
        runSQL(reviewSQLGenereator.getPopulateTableSQL(REVIEW.nameLowerCase()));
    }
}
