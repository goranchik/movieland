package com.goranchik.movieland.tools.utils.generator.impl;

import com.goranchik.movieland.tools.dto.MovieSearchRequestDto;
import com.goranchik.movieland.tools.utils.generator.SqlGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.goranchik.movieland.tools.Constants.*;

/**
 * Created by Ihor on 6/17/2016.
 */
@Service
public class MovieSqlGeneratorService implements SqlGeneratorService<MovieSearchRequestDto> {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String findAllMoviesSQL;

    @Autowired
    private String findMoviesByGenrePredicate;

    @Autowired
    private String findMoviesByTitlePredicate;

    @Autowired
    private String findMoviesByYearPredicate;

    @Autowired
    private String findMoviesByCountryPredicate;

    @Override
    public String getSearchSql(MovieSearchRequestDto searchDto) {
        StringBuilder searchRequest = new StringBuilder();
        if (searchDto != null
                && (hasText(searchDto.getGenre()) ||
                hasText(searchDto.getTitle()) ||
                hasText(searchDto.getYear()) ||
                hasText(searchDto.getCountry()))
                ) {
            boolean hasAnd = false;
            searchRequest.append(findAllMoviesSQL).append(WHERE);
            if (hasText(searchDto.getGenre())) {
                searchRequest.append(String.format(findMoviesByGenrePredicate, searchDto.getGenre()))
                        .append(AND);
                hasAnd = true;
            }
            if (hasText(searchDto.getTitle())) {
                String title = LIKE_PERCENT + searchDto.getTitle() + LIKE_PERCENT;
                searchRequest.append(String.format(findMoviesByTitlePredicate, title, title))
                        .append(AND);
                hasAnd = true;
            }
            if (hasText(searchDto.getYear())) {
                searchRequest.append(String.format(findMoviesByYearPredicate, searchDto.getYear()))
                        .append(AND);
                hasAnd = true;
            }
            if (hasText(searchDto.getCountry())) {
                searchRequest.append(String.format(findMoviesByCountryPredicate, searchDto.getCountry()));
            } else {
                if (hasAnd) {
                    int length = searchRequest.length();
                    searchRequest.delete(length - AND.length(), length);
                }
            }
        }
        log.info("Generated movie search request to database {}", searchRequest.toString());
        return searchRequest.toString();
    }

    private boolean hasText(String s) {
        return (s != null && s.length() != 0);
    }
}
