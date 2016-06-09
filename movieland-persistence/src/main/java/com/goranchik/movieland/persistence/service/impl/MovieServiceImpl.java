package com.goranchik.movieland.persistence.service.impl;

import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.persistence.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieDao movieDao;

    @Override
    public Movie findById(int id) {
        return movieDao.findById(id);
    }

    @Override
    public List<Movie> findAll() {
        return movieDao.findAll();
    }
}
