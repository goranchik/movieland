package com.goranchik.movieland.client.utils.converter.impl;

import com.goranchik.movieland.client.utils.converter.EntityConverter;
import com.goranchik.movieland.client.web.dto.MovieSingleViewDto;
import com.goranchik.movieland.persistence.dao.ReviewDao;
import com.goranchik.movieland.persistence.entity.Country;
import com.goranchik.movieland.persistence.entity.Genre;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.persistence.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.goranchik.movieland.tools.Constants.MOIVE_DELIMITER;

/**
 * Created by Ihor on 6/9/2016.
 */
@Component(EntityConverter.MOVIE_SINGLE_VIEW_CONVERTER)
public class MovieSingleViewConverter<T, M> implements EntityConverter<Movie, MovieSingleViewDto> {

    private static final Long REVIEW_LIMIT = 2L;

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public MovieSingleViewDto convert(Movie movie) {
        MovieSingleViewDto dto = new MovieSingleViewDto();
        dto.setTitle(movie.getName() + MOIVE_DELIMITER + movie.getNameOriginal());
        dto.setYearOfRelease(movie.getYear());
        dto.setCountries(movie.getCountries()
                .stream().map(Country::getName)
                .collect(Collectors.toSet()));
        dto.setGenres(movie.getGenres()
                .stream().map(Genre::getName)
                .collect(Collectors.toSet()));
        dto.setDescription(movie.getDescription());
        dto.setReviews(reviewDao.findByMovieId(movie.getId())
                .stream().limit(REVIEW_LIMIT)
                .collect(Collectors.toMap(
                        review -> review.getReviewer().getName(),
                        Review::getFeedback))
                );
        dto.setRating(movie.getRating());
        return dto;
    }
}
