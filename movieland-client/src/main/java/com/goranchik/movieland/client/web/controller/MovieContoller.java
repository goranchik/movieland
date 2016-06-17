package com.goranchik.movieland.client.web.controller;

import com.goranchik.movieland.client.utils.converter.JsonJacksonConverter;
import com.goranchik.movieland.client.web.dto.MovieMultipleViewDto;
import com.goranchik.movieland.tools.dto.MovieSearchRequestDto;
import com.goranchik.movieland.client.web.dto.MovieSingleViewDto;
import com.goranchik.movieland.client.web.service.MovieViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ihor on 6/8/2016.
 */
@RestController
@RequestMapping(value = "/v1", produces = "text/plain;charset=UTF-8")
public class MovieContoller {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    MovieViewService movieViewService;

    @Autowired
    JsonJacksonConverter jsonConverter;

    @RequestMapping("/movie/{movieId}")
    public String findById(@PathVariable int movieId){
        log.info("Sending request to find movie by id = {}", movieId);
        long startTime = System.currentTimeMillis();
        MovieSingleViewDto movie = movieViewService.findById(movieId);
        String genreJson = jsonConverter.toJson(movie);
        log.info("Movie {} is received. It took {} ms", genreJson, System.currentTimeMillis() - startTime);
        return genreJson;
    }

    @RequestMapping("/movies")
    public String findAll(){
        log.info("Sending request to find all movies");
        long startTime = System.currentTimeMillis();
        List<MovieMultipleViewDto> movies = movieViewService.findAll();
        String moviesJson = jsonConverter.toJson(movies);
        log.info("All movies {} are received. It took {} ms", moviesJson, System.currentTimeMillis() - startTime);
        return moviesJson;
    }

    @RequestMapping(value = "/movie/search", method = RequestMethod.POST)
    public String findBySearchRequest(@RequestBody String request){
        MovieSearchRequestDto movieRequest =
                jsonConverter.jsonToObj(request, MovieSearchRequestDto.class);
        List<MovieMultipleViewDto> movies = movieViewService.findBySearchRequest(movieRequest);
        String moviesJson = jsonConverter.toJson(movies);
        return moviesJson;
    }
}
