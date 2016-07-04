package com.goranchik.movieland.client.converter.impl;

import com.goranchik.movieland.client.converter.EntityConverter;
import com.goranchik.movieland.client.security.SecurityPrincipalHolder;
import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.entity.Review;
import com.goranchik.movieland.tools.dto.review.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class ReviewConverter implements EntityConverter<ReviewDto, Review> {
    @Autowired
    private MovieDao movieDao;

    @Override
    public Review convert(ReviewDto reviewDto) {
        Review review = new Review();
        review.setReviewer(SecurityPrincipalHolder.get().getUser());
        review.setMovie(movieDao.findById(reviewDto.getMovieId()));
        review.setFeedback(reviewDto.getReview());
        return review;
    }
}
