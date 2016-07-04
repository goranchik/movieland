package com.goranchik.movieland.persistence.dao;


import com.goranchik.movieland.persistence.entity.Country;

import java.util.List;
import java.util.Set;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface CountryDao {
    Country findById(int id);
    List<Country> findAll();
    Set<Country> findByMovieId(int id);
}
