package com.goranchik.movieland.persistence.service;

import com.goranchik.movieland.persistence.entity.Movie;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface MovieService {
    Movie findById(int id);
    List<Movie> findAll();
}
