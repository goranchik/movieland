package com.goranchik.movieland.client.converter.impl;

import com.goranchik.movieland.client.converter.EntityConverter;
import com.goranchik.movieland.persistence.entity.Genre;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.tools.dto.movie.view.MovieMultipleDto;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.goranchik.movieland.tools.Constants.MOVIE_DELIMITER;

/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class MovieMultipleConverter implements EntityConverter<Movie, MovieMultipleDto> {
    @Override
    public MovieMultipleDto convert(Movie movie) {
        MovieMultipleDto dto = new MovieMultipleDto();
        dto.setTitle(movie.getName() + MOVIE_DELIMITER + movie.getNameOriginal());
        dto.setYearOfRelease(movie.getYear());
        dto.setRating(movie.getRating());
        dto.setGenres(movie.getGenres()
                .stream().map(Genre::getName)
                .collect(Collectors.toSet()));
        return dto;
    }
}
