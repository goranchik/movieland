package com.goranchik.movieland.persistence.dao.jdbc.mapper;


import com.goranchik.movieland.persistence.entity.Country;
import com.goranchik.movieland.persistence.entity.Genre;
import com.goranchik.movieland.persistence.entity.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.goranchik.movieland.tools.Constants.ENTITY_DELIMITER;
import static com.goranchik.movieland.tools.Constants.FIELD_ID;
import static com.goranchik.movieland.tools.enums.Table.MOVIE;

/**
 * Created by Ihor on 6/8/2016.
 */
public class MovieBatchRowMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getInt(FIELD_ID));
        movie.setName(rs.getString(MOVIE.getFieldSet().get(0)));
        movie.setNameOriginal(rs.getString(MOVIE.getFieldSet().get(1)));
        movie.setYear(rs.getInt(MOVIE.getFieldSet().get(2)));
        movie.setDescription(rs.getString(MOVIE.getFieldSet().get(3)));
        movie.setRating(rs.getFloat(MOVIE.getFieldSet().get(4)));
        movie.setPrice(rs.getFloat(MOVIE.getFieldSet().get(5)));
        movie.setGenres(
                Arrays.stream(rs.getString("genres").split(ENTITY_DELIMITER))
                        .map(g -> new Genre(Integer.parseInt(g))).collect(Collectors.toSet()));
        movie.setCountries(
                Arrays.stream(rs.getString("countries").split(ENTITY_DELIMITER))
                        .map(c -> new Country(Integer.parseInt(c))).collect(Collectors.toSet()));
        return movie;
    }
}
