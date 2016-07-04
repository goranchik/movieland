package com.goranchik.movieland.persistence.dao;


import com.goranchik.movieland.persistence.entity.Genre;

import java.util.List;
import java.util.Set;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface GenreDao {
    Genre findById(int id);
    List<Genre> findAll();
    Set<Genre> findByMovieId(int id);
}
