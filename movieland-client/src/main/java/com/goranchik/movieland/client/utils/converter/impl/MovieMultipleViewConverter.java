package com.goranchik.movieland.client.utils.converter.impl;

import com.goranchik.movieland.client.utils.converter.EntityConverter;
import com.goranchik.movieland.client.web.dto.MovieMultipleViewDto;
import com.goranchik.movieland.client.web.dto.MovieSingleViewDto;
import com.goranchik.movieland.persistence.entity.Genre;
import com.goranchik.movieland.persistence.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.goranchik.movieland.tools.Constants.MOIVE_DELIMITER;

/**
 * Created by Ihor on 6/9/2016.
 */
@Component(EntityConverter.MOVIE_MULTIPLE_VIEW_CONVERTER)
public class MovieMultipleViewConverter<T, M> implements EntityConverter<Movie, MovieMultipleViewDto> {
    @Override
    public MovieMultipleViewDto convert(Movie movie) {
        MovieMultipleViewDto dto = new MovieMultipleViewDto();
        dto.setTitle(movie.getName() + MOIVE_DELIMITER + movie.getNameOriginal());
        dto.setYearOfRelease(movie.getYear());
        dto.setRating(movie.getRating());
        dto.setGenres(movie.getGenres()
                .stream().map(Genre::getName)
                .collect(Collectors.toSet()));
        return dto;
    }
}
