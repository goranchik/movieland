package com.goranchik.movieland.persistence.dao.jdbc.mapper;

import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.persistence.entity.Review;
import com.goranchik.movieland.persistence.entity.User;
import com.goranchik.movieland.tools.utils.PropTools;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.goranchik.movieland.tools.Table.REVIEW;

/**
 * Created by Ihor on 6/8/2016.
 */
public class ReviewRowMapper implements RowMapper<Review> {

    private Properties dbProperties = PropTools.getDBProperties();

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setMovie(new Movie(rs.getInt(REVIEW.fieldSet(dbProperties).get(0))));
        review.setReviewer(new User(rs.getInt(REVIEW.fieldSet(dbProperties).get(1))));
        review.setFeedback(rs.getString(REVIEW.fieldSet(dbProperties).get(2)));
        return review;
    }
}
