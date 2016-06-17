package com.goranchik.movieland.persistence.dao.jdbc.mapper;

import com.goranchik.movieland.persistence.entity.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Ihor on 6/10/2016.
 */
public class ReviewRowMapperTest {

    private static final String TEST_MOVIE_NAME = "Test movie name";
    private static final String TEST_MOVIE_NAME_ORIGINAL = "Test movie name original";
    private static final String TEST_DESCRIPTION = "Test description";
    private static final String TEST_GENRE_NAME = "Test genre name";
    private static final String TEST_COUNTRY_NAME = "Test country name";
    private static final String TEST_REVIEW_FEEDBACK = "Test review feedback";

    private static final int TEST_MOVIE_ID = 1;
    private static final int TEST_YEAR = 1983;
    private static final int TEST_GENRE_ID = 1;
    private static final int TEST_COUNTRY_ID = 1;

    private static final int TEST_USER_ID = 1;

    private static final float TEST_RATING = 10.0F;
    private static final float TEST_PRICE = 120.0F;


    @Test
    public void testMapRowWithProperMovie() throws SQLException {

        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt(any()))
                .thenReturn(TEST_MOVIE_ID)
                .thenReturn(TEST_USER_ID);

        when(resultSet.getString(any()))
                .thenReturn(TEST_REVIEW_FEEDBACK);

        ReviewRowMapper reviewRowMapper = new ReviewRowMapper();
        Review actualReview = reviewRowMapper.mapRow(resultSet, 0);

        assertEquals(actualReview.getMovie().getId(), TEST_MOVIE_ID);
        assertEquals(actualReview.getReviewer().getId(), TEST_USER_ID);
        assertEquals(actualReview.getFeedback(), TEST_REVIEW_FEEDBACK);
    }
}
