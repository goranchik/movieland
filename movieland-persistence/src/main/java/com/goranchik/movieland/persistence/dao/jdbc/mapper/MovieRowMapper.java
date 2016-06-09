package com.goranchik.movieland.persistence.dao.jdbc.mapper;

import com.goranchik.movieland.persistence.dao.CountryDao;
import com.goranchik.movieland.persistence.dao.GenreDao;
import com.goranchik.movieland.persistence.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Ihor on 6/8/2016.
 */
@AllArgsConstructor @NoArgsConstructor
public class MovieRowMapper implements RowMapper<Movie> {

    private GenreDao genreDao;
    private CountryDao countryDao;

    // TODO - replace hardcode column names
    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        int movieId = rs.getInt("id");
        movie.setId(movieId);
        movie.setName(rs.getString("name"));
        movie.setNameOriginal(rs.getString("name_original"));
        movie.setYear(rs.getInt("year"));
        movie.setDescription(rs.getString("description"));
        movie.setRating(rs.getFloat("rating"));
        movie.setPrice(rs.getFloat("price"));
        movie.setGenres(genreDao.findByMovieId(movieId));
        movie.setCountries(countryDao.findByMovieId(movieId));
        return movie;
    }
}
