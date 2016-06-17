package com.goranchik.movieland.client.web.service.impl;

import com.goranchik.movieland.client.utils.converter.EntityConverter;
import com.goranchik.movieland.client.web.dto.MovieMultipleViewDto;
import com.goranchik.movieland.tools.dto.MovieSearchRequestDto;
import com.goranchik.movieland.client.web.dto.MovieSingleViewDto;
import com.goranchik.movieland.client.web.service.MovieViewService;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class MovieViewServiceImpl implements MovieViewService {

    @Autowired
    MovieService movieService;

    @Autowired
    @Qualifier(EntityConverter.MOVIE_SINGLE_VIEW_CONVERTER)
    EntityConverter<Movie, MovieSingleViewDto> singleConverter;

    @Autowired
    @Qualifier(EntityConverter.MOVIE_MULTIPLE_VIEW_CONVERTER)
    EntityConverter<Movie, MovieMultipleViewDto> multipleConverter;

    @Override
    public MovieSingleViewDto findById(int id) {
        return singleConverter.convert(movieService.findById(id));
    }

    @Override
    public List<MovieMultipleViewDto> findAll() {
        return movieService.findAll()
                .stream().map(movie -> multipleConverter.convert(movie))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovieMultipleViewDto> findBySearchRequest(MovieSearchRequestDto movieRequest) {
        return movieService.findBySearchRequest(movieRequest)
                .stream().map(movie -> multipleConverter.convert(movie))
                .collect(Collectors.toList());
    }
}
