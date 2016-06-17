package com.goranchik.movieland.service.impl;

import com.goranchik.movieland.persistence.dao.CountryDao;
import com.goranchik.movieland.persistence.dao.GenreDao;
import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private GenreDao genreDao;

    @Autowired
    private CountryDao countryDao;

    @Override
    public Movie findById(int id) {
        Movie movie = movieDao.findById(id);
        movie.setGenres(genreDao.findByMovieId(id));
        movie.setCountries(countryDao.findByMovieId(id));
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = movieDao.findAll();
        movies.stream().forEach(movie ->
                {
                    int movieId = movie.getId();
                    movie.setGenres(genreDao.findByMovieId(movieId));
                    movie.setCountries(countryDao.findByMovieId(movieId));
                }
        );
        return movies;
    }
}
