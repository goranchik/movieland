package com.goranchik.movieland.persistence.dao.jdbc.postgres;

import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.dao.jdbc.mapper.MovieBatchRowMapper;
import com.goranchik.movieland.persistence.entity.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Ihor on 6/8/2016.
 */
@Repository
public class JdbcMovieDao implements MovieDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private BeanPropertyRowMapper<Movie> mapper = new BeanPropertyRowMapper<>(Movie.class);
    private MovieBatchRowMapper batchMapper = new MovieBatchRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String findMovieByIdSQL;

    @Autowired
    private String findMovieByIdSQLBatch;

    @Override
    public Movie findById(int id) {
        return findByIdCommon(id, findMovieByIdSQL, mapper);
    }

    @Override
    public List<Movie> findBySql(String sql) {
        return findBySqlCommon(sql, mapper);
    }

    @Override
    public Movie findByIdBatch(int id) {
        return findByIdCommon(id, findMovieByIdSQLBatch, batchMapper);
    }

    @Override
    public List<Movie> findBySqlBatch(String sql) {
        return findBySqlCommon(sql, batchMapper);
    }

    private <T extends RowMapper<Movie>> Movie findByIdCommon(int id, String sql, T rowMapper){
        log.info("Start query {} to find movie by id {} from DB", sql, id);
        long startTime = System.currentTimeMillis();
        Movie movie = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        log.info("Finish query to find movie by id {} from DB. It took {} ms",
                id, System.currentTimeMillis() - startTime);
        return movie;
    }

    private <T extends RowMapper<Movie>> List<Movie> findBySqlCommon(String sql, T rowMapper) {
        log.info("Start query {} to find movies from DB", sql);
        long startTime = System.currentTimeMillis();
        List<Movie> movies = jdbcTemplate.query(sql, rowMapper);
        log.info("Finish query to find movies from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return movies;
    }


}
