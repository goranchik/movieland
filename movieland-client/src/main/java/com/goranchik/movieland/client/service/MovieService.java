package com.goranchik.movieland.client.service;

import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.tools.dto.RequestDto;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface MovieService {
    Movie findById(int id);
    Movie findByIdBatch(int id);
    <T extends RequestDto> List<Movie> findAll(T sortDto);
    <T extends RequestDto> List<Movie> findAllBatch(T sortDto);
    <M extends RequestDto> List<Movie> findBySearchRequest(M movieRequest);
}

