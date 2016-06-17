package com.goranchik.movieland.service;

import com.goranchik.movieland.persistence.entity.Review;

import java.util.List;

/**
 * Created by Ihor on 6/13/2016.
 */
public interface ReviewService {
    List<Review> findAll();
    List<Review> findByMovieId(int id);
}
