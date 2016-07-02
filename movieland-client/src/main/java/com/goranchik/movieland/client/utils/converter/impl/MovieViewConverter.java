package com.goranchik.movieland.client.utils.converter.impl;

import com.goranchik.movieland.client.utils.converter.EntityConverter;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.tools.dto.view.MovieMultipleViewDto;
import com.goranchik.movieland.tools.dto.view.MovieSingleViewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class MovieViewConverter {

    @Autowired
    private EntityConverter<Movie, MovieSingleViewDto> singleConverter;

    @Autowired
    private EntityConverter<Movie, MovieMultipleViewDto> multipleConverter;

    public <T> T convert(Movie movie, Class<T> clazz) {
        if (clazz.equals(MovieSingleViewDto.class)){
            return (T) singleConverter.convert(movie);
        } else if (clazz.equals(MovieMultipleViewDto.class)){
            return (T) multipleConverter.convert(movie);
        }
        return null;
    }
}
