package com.goranchik.movieland.persistence.dao.jdbc.mapper;

import com.goranchik.movieland.persistence.dao.*;
import com.goranchik.movieland.persistence.entity.Review;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ihor on 6/8/2016.
 */
@AllArgsConstructor @NoArgsConstructor
public class ReviewRowMapper implements RowMapper<Review> {

    private MovieDao movieDao;
    private UserDao userDao;

    // TODO - replace hardcode column names
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setMovie(movieDao.findById(rs.getInt("movie_id")));
        review.setReviewer(userDao.findById(rs.getInt("reviewer_id")));
        review.setFeedback(rs.getString("feedback"));
        return review;
    }
}
