package com.goranchik.movieland.persistence.utils;

import com.goranchik.movieland.persistence.utils.generator.SQLGenerator;
import javafx.scene.control.Tab;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

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

    private Properties getDBProperties() throws IOException {
        Resource resource = new ClassPathResource("/config/db.properties");
        return PropertiesLoaderUtils.loadProperties(resource);
    }

    @PostConstruct
    public void initDBLoad() throws IOException {
        Properties props = getDBProperties();
        runSQL(genreSQLGenereator.getPopulateTableSQL(GENRE.name().toLowerCase(), props));
        runSQL(userSQLGenereator.getPopulateTableSQL(USERS.name().toLowerCase(), props));
        runSQL(movieSQLGenereator.getPopulateTableSQL(MOVIE.name().toLowerCase(), props));
        runSQL(reviewSQLGenereator.getPopulateTableSQL(REVIEW.name().toLowerCase(), props));
    }
}
