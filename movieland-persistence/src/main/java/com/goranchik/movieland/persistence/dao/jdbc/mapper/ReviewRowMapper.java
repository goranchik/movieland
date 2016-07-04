package com.goranchik.movieland.persistence.dao.jdbc.mapper;


import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.persistence.entity.Review;
import com.goranchik.movieland.persistence.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.goranchik.movieland.tools.enums.Table.REVIEW;

/**
 * Created by Ihor on 6/8/2016.
 */
public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setMovie(new Movie(rs.getInt(REVIEW.getFieldSet().get(0))));
        review.setReviewer(new User(rs.getInt(REVIEW.getFieldSet().get(1))));
        review.setFeedback(rs.getString(REVIEW.getFieldSet().get(2)));
        return review;
    }
}
