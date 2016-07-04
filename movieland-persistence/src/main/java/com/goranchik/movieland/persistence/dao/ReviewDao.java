package com.goranchik.movieland.persistence.dao;

import com.goranchik.movieland.persistence.entity.Review;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface ReviewDao {
    List<Review> findAll();
    List<Review> findByMovieId(int id);
}
