package com.goranchik.movieland.persistence.dao.jdbc.postgres;

import com.goranchik.movieland.persistence.dao.ReviewDao;
import com.goranchik.movieland.persistence.dao.jdbc.mapper.ReviewRowMapper;
import com.goranchik.movieland.persistence.entity.Review;
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
    private ReviewRowMapper mapper = new ReviewRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String findAllReviewsSQL;

    @Autowired
    private String findReviewsByMovieIdSQL;

    @Autowired
    private String insertReviewSQL;

    @Autowired
    private String deleteReviewSQL;


    @Override
    public List<Review> findAll() {
        return jdbcTemplate.query(findAllReviewsSQL, mapper);
    }

    @Override
    public List<Review> findByMovieId(int id) {
        return jdbcTemplate.query(findReviewsByMovieIdSQL, new Object[]{id}, mapper);
    }

    @Override
    public Review add(Review review) {
        jdbcTemplate.update(insertReviewSQL, review.getMovie().getId(), review.getReviewer().getId(), review.getFeedback());
        return review;
    }

    @Override
    public Review remove(Review review) {
        jdbcTemplate.update(deleteReviewSQL, review.getMovie().getId(), review.getReviewer().getId());
        return review;
    }

}
