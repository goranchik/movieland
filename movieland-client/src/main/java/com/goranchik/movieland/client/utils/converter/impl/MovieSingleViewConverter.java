package com.goranchik.movieland.client.utils.converter.impl;

import com.goranchik.movieland.client.utils.converter.EntityConverter;
import com.goranchik.movieland.tools.dto.view.MovieSingleViewDto;
import com.goranchik.movieland.persistence.entity.Country;
import com.goranchik.movieland.persistence.entity.Genre;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.persistence.entity.Review;
import com.goranchik.movieland.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.goranchik.movieland.tools.Constants.MOVIE_DELIMITER;
import static com.goranchik.movieland.tools.Constants.REVIEW_LIMIT;

/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class MovieSingleViewConverter implements EntityConverter<Movie, MovieSingleViewDto> {

    @Autowired
    private ReviewService reviewService;

    @Override
    public MovieSingleViewDto convert(Movie movie) {
        MovieSingleViewDto dto = new MovieSingleViewDto();
        dto.setTitle(movie.getName() + MOVIE_DELIMITER + movie.getNameOriginal());
        dto.setYearOfRelease(movie.getYear());
        dto.setCountries(movie.getCountries()
                .stream().map(Country::getName)
                .collect(Collectors.toSet()));
        dto.setGenres(movie.getGenres()
                .stream().map(Genre::getName)
                .collect(Collectors.toSet()));
        dto.setDescription(movie.getDescription());
        dto.setReviews(reviewService.findByMovieId(movie.getId())
                .stream().limit(REVIEW_LIMIT)
                .collect(Collectors.toMap(
                        review -> review.getReviewer().getName(),
                        Review::getFeedback))
                );
        dto.setRating(movie.getRating());
        return dto;
    }
}
