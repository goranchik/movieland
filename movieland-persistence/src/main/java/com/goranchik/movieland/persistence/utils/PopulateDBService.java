package com.goranchik.movieland.persistence.utils;

import com.goranchik.movieland.tools.utils.generator.SqlLaunchGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

import static com.goranchik.movieland.tools.Constants.*;
import static com.goranchik.movieland.tools.enums.Table.*;

/**
 * Created by Ihor on 6/8/2016.
 */
@Service
public class PopulateDBService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier(GENRE_SQL_LAUNCH_GENERATOR)
    private SqlLaunchGeneratorService genreSqlGenereator;

    @Autowired
    @Qualifier(USER_SQL_LAUNCH_GENERATOR)
    private SqlLaunchGeneratorService userSqlGenereator;

    @Autowired
    @Qualifier(MOVIE_SQL_LAUNCH_GENERATOR)
    private SqlLaunchGeneratorService movieSqlGenereator;

    @Autowired
    @Qualifier(REVIEW_SQL_LAUNCH_GENERATOR)
    private SqlLaunchGeneratorService reviewSqlGenereator;

    private void runSQL(String sql) {
        jdbcTemplate.execute(sql);
    }

    @PostConstruct
    public void launch() throws IOException {
        runSQL(genreSqlGenereator.getPopulateTableSql(GENRE.getName()));
        runSQL(userSqlGenereator.getPopulateTableSql(USERS.getName()));
        runSQL(movieSqlGenereator.getPopulateTableSql(MOVIE.getName()));
        runSQL(reviewSqlGenereator.getPopulateTableSql(REVIEW.getName()));
    }
}
