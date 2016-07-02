package com.goranchik.movieland.tools.utils.generator.impl;


import com.goranchik.movieland.tools.dto.MovieSearchDto;
import com.goranchik.movieland.tools.dto.MovieSortDto;
import com.goranchik.movieland.tools.utils.generator.SqlGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.goranchik.movieland.tools.Constants.*;
import static com.goranchik.movieland.tools.enums.Table.MOVIE;

/**
 * Created by Ihor on 6/17/2016.
 */
@Service
public class MovieSqlGeneratorService implements SqlGeneratorService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String findAllMoviesSQL;

    @Autowired
    private String findAllMoviesSQLBatch;

    @Autowired
    private String findMoviesByGenrePredicate;

    @Autowired
    private String findMoviesByTitlePredicate;

    @Autowired
    private String findMoviesByYearPredicate;

    @Autowired
    private String findMoviesByCountryPredicate;

    @Override
    public String getSearchSql(MovieSearchDto searchDto) {
        StringBuilder searchQuery = new StringBuilder();
        if (searchDto != null
                && (hasText(searchDto.getGenre()) ||
                hasText(searchDto.getTitle()) ||
                hasText(searchDto.getYear()) ||
                hasText(searchDto.getCountry()))
                ) {
            boolean hasAnd = false;
            searchQuery.append(findAllMoviesSQL).append(WHERE);
            if (hasText(searchDto.getGenre())) {
                searchQuery.append(String.format(findMoviesByGenrePredicate, searchDto.getGenre()))
                        .append(AND);
                hasAnd = true;
            }
            if (hasText(searchDto.getTitle())) {
                String title = LIKE_PERCENT + searchDto.getTitle() + LIKE_PERCENT;
                searchQuery.append(String.format(findMoviesByTitlePredicate, title, title))
                        .append(AND);
                hasAnd = true;
            }
            if (hasText(searchDto.getYear())) {
                searchQuery.append(String.format(findMoviesByYearPredicate, searchDto.getYear()))
                        .append(AND);
                hasAnd = true;
            }
            if (hasText(searchDto.getCountry())) {
                searchQuery.append(String.format(findMoviesByCountryPredicate, searchDto.getCountry()));
            } else {
                if (hasAnd) {
                    int length = searchQuery.length();
                    searchQuery.delete(length - AND.length(), length);
                }
            }
        }
        log.info("Generated movie search type to database {}", searchQuery.toString());
        return searchQuery.toString();
    }

    @Override
    public String getSortSql(MovieSortDto sortDto) {
        return findAllMoviesSQL + getSortStatement(sortDto);
    }

    @Override
    public String getSortSqlBatch(MovieSortDto sortDto) {
        return findAllMoviesSQLBatch + getSortStatement(sortDto);
    }

    private String getSortStatement(MovieSortDto sortDto){
        StringBuilder query = new StringBuilder();
        if (sortDto != null
                && (hasText(sortDto.getRatingOrder()) ||
                hasText(sortDto.getPriceOrder())
        )) {
            boolean hasComma = false;
            query.append(ORDER_BY);
            if (hasText(sortDto.getRatingOrder())) {
                query.append(MOVIE.getFieldSet().get(4))
                        .append(SPACE).append(sortDto.getRatingOrder())
                        .append(SQL_DELIMITER);
                hasComma = true;
            }
            if (hasText(sortDto.getPriceOrder())) {
                query.append(MOVIE.getFieldSet().get(5))
                        .append(SPACE).append(sortDto.getPriceOrder());
            } else {
                if (hasComma) {
                    int length = query.length();
                    query.delete(length - SQL_DELIMITER.length(), length);
                }
            }
        }
        log.info("Generated movie sort query to database {}", query.toString());
        return query.toString();
    }

    private boolean hasText(String s) {
        return (s != null && s.length() != 0);
    }

}
