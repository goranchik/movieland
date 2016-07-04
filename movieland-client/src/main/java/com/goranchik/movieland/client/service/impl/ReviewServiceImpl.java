package com.goranchik.movieland.client.service.impl;

import com.goranchik.movieland.client.converter.impl.Converter;
import com.goranchik.movieland.client.service.ReviewService;
import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.dao.ReviewDao;
import com.goranchik.movieland.persistence.dao.UserDao;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.persistence.entity.Review;
import com.goranchik.movieland.persistence.entity.User;
import com.goranchik.movieland.tools.dto.RequestDto;
import com.goranchik.movieland.tools.utils.generator.SqlGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ihor on 6/13/2016.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private Converter converter;

    @Override
    public List<Review> findAll() {
        List<Review> reviews = reviewDao.findAll();
        reviews.stream().forEach(this::populate);
        return reviews;
    }

    @Override
    public List<Review> findByMovieId(int id) {
        List<Review> reviews = reviewDao.findByMovieId(id);
        reviews.stream().forEach(this::populate);
        return reviews;
    }

    @Override
    public <T extends RequestDto> Review add(T addReviewDto) {
        Review review = converter.convert(addReviewDto, Review.class);
        return reviewDao.add(review);
    }

    @Override
    public <T extends RequestDto> Review remove(T removeReviewDto) {
        Review review = converter.convert(removeReviewDto, Review.class);
        return reviewDao.remove(review);

    }

    private Review populate(Review review){
        Movie movie = movieDao.findById(review.getMovie().getId());
        review.setMovie(movie);

        User reviewer = userDao.findById(review.getReviewer().getId());
        review.setReviewer(reviewer);
        return review;
    }

}
