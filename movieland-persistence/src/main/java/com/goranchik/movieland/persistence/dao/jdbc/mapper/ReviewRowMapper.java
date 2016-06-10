package com.goranchik.movieland.persistence.dao.jdbc.mapper;

import com.goranchik.movieland.persistence.dao.*;
import com.goranchik.movieland.persistence.entity.Review;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.goranchik.movieland.persistence.utils.Table.REVIEW;

/**
 * Created by Ihor on 6/8/2016.
 */
@AllArgsConstructor @NoArgsConstructor
public class ReviewRowMapper implements RowMapper<Review> {

    private MovieDao movieDao;
    private UserDao userDao;
    private Properties props;

    @Override
    public Review mapRow(ResultSet rs, int rowNum) throws SQLException {
        Review review = new Review();
        review.setMovie(movieDao.findById(rs.getInt(REVIEW.fieldSet(props).get(0))));
        review.setReviewer(userDao.findById(rs.getInt(REVIEW.fieldSet(props).get(1))));
        review.setFeedback(rs.getString(REVIEW.fieldSet(props).get(2)));
        return review;
    }
}
