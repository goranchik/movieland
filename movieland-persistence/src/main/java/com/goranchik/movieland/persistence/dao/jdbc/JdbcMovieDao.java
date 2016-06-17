package com.goranchik.movieland.persistence.dao.jdbc;

import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
@Repository
public class JdbcMovieDao implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private BeanPropertyRowMapper<Movie> mapper = new BeanPropertyRowMapper<>(Movie.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String findMovieByIdSQL;

    @Autowired
    private String findAllMoviesSQL;

    @Override
    public Movie findById(int id) {
        log.info("Start query to find movie by id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(findMovieByIdSQL,
                new Object[]{id}, mapper);
        log.info("Finish query to find movie by id {} from DB. It took {} ms",
                id, System.currentTimeMillis() - startTime);
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        log.info("Start query to find all movies from DB");
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(findAllMoviesSQL, mapper);
        log.info("Finish query to find all movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movies;
    }

    @Override
    public List<Movie> findBySearchRequest(String searchRequest) {
        List<Movie> movies = jdbcTemplate.query(searchRequest, mapper);
        return movies;
    }
}
