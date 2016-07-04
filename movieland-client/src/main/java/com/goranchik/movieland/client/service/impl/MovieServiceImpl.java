package com.goranchik.movieland.client.service.impl;

import com.goranchik.movieland.client.service.MovieService;
import com.goranchik.movieland.persistence.dao.CountryDao;
import com.goranchik.movieland.persistence.dao.GenreDao;
import com.goranchik.movieland.persistence.dao.MovieDao;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.tools.dto.RequestDto;
import com.goranchik.movieland.tools.dto.movie.MovieSearchDto;
import com.goranchik.movieland.tools.dto.movie.MovieSortDto;
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
    private SqlGeneratorService sqlGenerator;

    @Override
    public Movie findById(int id) {
        Movie movie = movieDao.findById(id);
        return populate(movie);
    }

    @Override
    public Movie findByIdBatch(int id) {
        Movie movie = movieDao.findByIdBatch(id);
        return populateBatch(movie);
    }

    @Override
    public <T extends RequestDto> List<Movie> findAll(T sortDto) {
        String sql = sqlGenerator.getSortSql((MovieSortDto) sortDto);
        List<Movie> movies = movieDao.findBySql(sql);
        return movies.stream().map(this::populate).collect(Collectors.toList());
    }

    @Override
    public <T extends RequestDto> List<Movie> findAllBatch(T sortDto) {
        String sql = sqlGenerator.getSortSqlBatch((MovieSortDto) sortDto);
        List<Movie> movies = movieDao.findBySqlBatch(sql);
        return movies.stream().map(this::populateBatch).collect(Collectors.toList());
    }


    @Override
    public <M extends RequestDto> List<Movie> findBySearchRequest(M searchRequest) {
        String sql = sqlGenerator.getSearchSql((MovieSearchDto) searchRequest);
        List<Movie> movies = movieDao.findBySql(sql);
        return movies.stream().map(this::populate).collect(Collectors.toList());
    }

    private Movie populate(Movie movie){
        int id = movie.getId();
        movie.setGenres(genreDao.findByMovieId(id).stream()
                .map(g -> genreDao.findById(g.getId())).collect(Collectors.toSet()));
        movie.setCountries(countryDao.findByMovieId(id).stream()
                .map(c -> countryDao.findById(c.getId())).collect(Collectors.toSet())
        );
        return movie;
    }

    private Movie populateBatch(Movie movie){
        movie.setGenres(movie.getGenres().stream()
                .map(g -> genreDao.findById(g.getId())).collect(Collectors.toSet()));
        movie.setCountries(movie.getCountries().stream()
                .map(c -> countryDao.findById(c.getId())).collect(Collectors.toSet())
        );
        return movie;
    }
}
