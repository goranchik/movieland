package com.goranchik.movieland.service.impl;

import com.goranchik.movieland.persistence.dao.CountryDao;
import com.goranchik.movieland.persistence.dao.GenreDao;
import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.tools.dto.MovieSearchRequestDto;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.service.MovieService;
import com.goranchik.movieland.tools.utils.generator.SqlGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SqlGeneratorService<MovieSearchRequestDto> searchGenerator;

    @Override
    public Movie findById(int id) {
        Movie movie = movieDao.findById(id);
        return populate(movie);
    }

    @Override
    public List<Movie> findAll() {
        List<Movie> movies = movieDao.findAll();
        return movies.stream().map(this::populate).collect(Collectors.toList());
    }

    @Override
    public List<Movie> findBySearchRequest(MovieSearchRequestDto movieRequest) {
        String searchRequest = searchGenerator.getSearchSql(movieRequest);
        List<Movie> movies = movieDao.findBySearchRequest(searchRequest);
        return movies.stream().map(this::populate).collect(Collectors.toList());
    }

    private Movie populate(Movie movie){
        int id = movie.getId();
        movie.setGenres(genreDao.findByMovieId(id));
        movie.setCountries(countryDao.findByMovieId(id));
        return movie;
    }
}
