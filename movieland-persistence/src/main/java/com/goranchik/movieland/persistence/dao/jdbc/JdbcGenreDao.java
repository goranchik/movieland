package com.goranchik.movieland.persistence.dao.jdbc;

import com.goranchik.movieland.persistence.dao.GenreDao;
import com.goranchik.movieland.persistence.entity.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ihor on 6/8/2016.
 */
@Repository
public class JdbcGenreDao implements GenreDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String findGenreByIdSQL;

    @Autowired
    private String findAllGenresSQL;

    @Autowired
    private String findGenresByMovieIdSQL;

    @Override
    public Genre findById(int id) {
        log.info("Start query to find genre by id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Genre genre = jdbcTemplate.queryForObject(findGenreByIdSQL, new Object[]{id}, new BeanPropertyRowMapper<>(Genre.class));
        log.info("Finish query to find genre by id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return genre;
    }

    @Override
    public List<Genre> findAll() {
        log.info("Start query to find genres from DB");
        long startTime = System.currentTimeMillis();
        List<Genre> genres = jdbcTemplate.query(findAllGenresSQL,
                new BeanPropertyRowMapper<>(Genre.class));
        log.info("Finish query to find genres from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return genres;
    }

    @Override
    public Set<Genre> findByMovieId(int id) {
        log.info("Start query to find genres by movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        List<Genre> genres = jdbcTemplate.query(findGenresByMovieIdSQL, new Object[]{id},
                new BeanPropertyRowMapper<>(Genre.class));
        log.info("Finish query to find genres by movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        Set<Genre> genreSet = new HashSet<>(genres);
        return genreSet;
    }
}
