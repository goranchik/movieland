package com.goranchik.movieland.service;

import com.goranchik.movieland.tools.dto.MovieRestDto;
import com.goranchik.movieland.persistence.entity.Movie;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface MovieService {
    Movie findById(int id);
    Movie findByIdBatch(int id);
    <T extends MovieRestDto> List<Movie> findAll(T sortDto);
    <T extends MovieRestDto> List<Movie> findAllBatch(T sortDto);
    <M extends MovieRestDto> List<Movie> findBySearchRequest(M movieRequest);
}

