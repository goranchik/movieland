package com.goranchik.movieland.persistence.dao;

import com.goranchik.movieland.persistence.entity.Movie;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface MovieDao {
    Movie findById(int id);
    Movie findByIdBatch(int id);
    List<Movie> findBySql(String sql);
    List<Movie> findBySqlBatch(String sql);
}
