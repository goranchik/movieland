package com.goranchik.movieland.persistence.dao.jdbc;

import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.dao.ReviewDao;
import com.goranchik.movieland.persistence.dao.UserDao;
import com.goranchik.movieland.persistence.dao.jdbc.mapper.ReviewRowMapper;
import com.goranchik.movieland.persistence.entity.Review;
import com.goranchik.movieland.persistence.utils.PropertyDBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ihor on 6/9/2016.
 */
@Repository
public class JdbcReviewDao implements ReviewDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String findAllReviewsSQL;

    @Autowired
    private String findReviewsByMovieIdSQL;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private PropertyDBService propertyDBService;

    @Override
    public List<Review> findAll() {
        log.info("Start query to find all reviews from DB");
        long startTime = System.currentTimeMillis();
        List<Review> reviews = jdbcTemplate.query(findAllReviewsSQL,
                new ReviewRowMapper(movieDao, userDao, propertyDBService.getDBProperties()));
        log.info("Finish query to find all reviews from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return reviews;
    }

    @Override
    public List<Review> findByMovieId(int id) {
        log.info("Start query to find reviews by movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        List<Review> reviews = jdbcTemplate.query(findReviewsByMovieIdSQL, new Object[]{id},
                new ReviewRowMapper(movieDao, userDao, propertyDBService.getDBProperties()));
        log.info("Finish query to find reviews by movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return reviews;
    }

}
