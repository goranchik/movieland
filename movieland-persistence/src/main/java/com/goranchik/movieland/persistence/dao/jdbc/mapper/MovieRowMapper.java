package com.goranchik.movieland.persistence.dao.jdbc.mapper;

import com.goranchik.movieland.persistence.dao.CountryDao;
import com.goranchik.movieland.persistence.dao.GenreDao;
import com.goranchik.movieland.persistence.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.goranchik.movieland.persistence.utils.Table.MOVIE;
import static com.goranchik.movieland.tools.Constants.FIELD_ID;

/**
 * Created by Ihor on 6/8/2016.
 */
@AllArgsConstructor @NoArgsConstructor
public class MovieRowMapper implements RowMapper<Movie> {

    private GenreDao genreDao;
    private CountryDao countryDao;
    private Properties dbProperties;

    @Override
    public Movie mapRow(ResultSet rs, int rowNum) throws SQLException {
        Movie movie = new Movie();
        int movieId = rs.getInt(FIELD_ID);
        movie.setId(movieId);
        movie.setName(rs.getString(MOVIE.fieldSet(dbProperties).get(0)));
        movie.setNameOriginal(rs.getString(MOVIE.fieldSet(dbProperties).get(1)));
        movie.setYear(rs.getInt(MOVIE.fieldSet(dbProperties).get(2)));
        movie.setDescription(rs.getString(MOVIE.fieldSet(dbProperties).get(3)));
        movie.setRating(rs.getFloat(MOVIE.fieldSet(dbProperties).get(4)));
        movie.setPrice(rs.getFloat(MOVIE.fieldSet(dbProperties).get(5)));
        movie.setGenres(genreDao.findByMovieId(movieId));
        movie.setCountries(countryDao.findByMovieId(movieId));
        return movie;
    }
}
