package com.goranchik.movieland.persistence.service.impl;

import com.goranchik.movieland.persistence.dao.GenreDao;
import com.goranchik.movieland.persistence.entity.Genre;
import com.goranchik.movieland.persistence.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreDao genreDao;

    @Override
    public Genre findById(int id) {
        return genreDao.findById(id);
    }

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }
}
