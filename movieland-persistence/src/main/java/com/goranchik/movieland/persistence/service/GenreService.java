package com.goranchik.movieland.persistence.service;

import com.goranchik.movieland.persistence.entity.Genre;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface GenreService {
    Genre findById(int id);
    List<Genre> findAll();
}
