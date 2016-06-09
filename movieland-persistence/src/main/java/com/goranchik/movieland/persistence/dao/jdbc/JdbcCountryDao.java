package com.goranchik.movieland.persistence.dao.jdbc;

import com.goranchik.movieland.persistence.dao.CountryDao;
import com.goranchik.movieland.persistence.entity.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ihor on 6/8/2016.
 */
@Repository
public class JdbcCountryDao implements CountryDao {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String findCountryByIdSQL;

    @Autowired
    private String findAllCountriesSQL;

    @Autowired
    private String findCountriesByMovieIdSQL;

    @Override
    public Country findById(int id) {
        log.info("Start query to find country by id {} from DB", id);
        long startTime = System.currentTimeMillis();
        Country country = jdbcTemplate.queryForObject(findCountryByIdSQL, new BeanPropertyRowMapper<>(Country.class));
        log.info("Finish query to find country by id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return country;
    }

    @Override
    public List<Country> findAll() {
        log.info("Start query to find all countries from DB");
        long startTime = System.currentTimeMillis();
        List<Country> countries = jdbcTemplate.query(findAllCountriesSQL,
                new BeanPropertyRowMapper<>(Country.class));
        log.info("Finish query to find all countries from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return countries;
    }

    @Override
    public Set<Country> findByMovieId(int id) {
        log.info("Start query to find countries by movie id {} from DB", id);
        long startTime = System.currentTimeMillis();
        List<Country> countries = jdbcTemplate.query(findCountriesByMovieIdSQL, new Object[]{id},
                new BeanPropertyRowMapper<>(Country.class));
        log.info("Finish query to find countries by movie id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        Set<Country> countrySet = new HashSet<>(countries);
        return countrySet;
    }
}
