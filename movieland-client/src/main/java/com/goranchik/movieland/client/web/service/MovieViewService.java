package com.goranchik.movieland.client.web.service;

import com.goranchik.movieland.client.web.dto.MovieMultipleViewDto;
import com.goranchik.movieland.tools.dto.MovieSearchRequestDto;
import com.goranchik.movieland.client.web.dto.MovieSingleViewDto;

import java.util.List;

/**
 * Created by Ihor on 6/9/2016.
 */
public interface MovieViewService {
    MovieSingleViewDto findById(int id);
    List<MovieMultipleViewDto> findAll();
    List<MovieMultipleViewDto> findBySearchRequest(MovieSearchRequestDto movieRequest);
}
