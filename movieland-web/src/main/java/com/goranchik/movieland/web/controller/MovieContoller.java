package com.goranchik.movieland.web.controller;

import com.goranchik.movieland.client.service.MovieService;
import com.goranchik.movieland.tools.annotation.Request;
import com.goranchik.movieland.web.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static com.goranchik.movieland.tools.enums.RequestType.*;


/**
 * Created by Ihor on 6/8/2016.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.TEXT_PLAIN_VALUE)
public class MovieContoller {

    @Autowired
    private MovieService movieService;

    @Autowired
    private RequestService requestService;

    @RequestMapping("/movie/{movieId}")
    @Request(type = GET_MOVIE_BY_ID_REQUEST)
    public String findById(@PathVariable int movieId) {
        return requestService.handleOneByParams(
                movieService::findByIdBatch,
                GET_MOVIE_BY_ID_REQUEST.getViewDto(),
                Integer.class,
                new Object[]{movieId});
    }

    @RequestMapping("/movies")
    @Request(type = GET_ALL_MOVIE_REQUEST)
    public String findAll(@RequestParam(required = false) String rating,
                          @RequestParam(required = false) String price) {
        return requestService.handleListByParams(
                movieService::findAllBatch,
                GET_ALL_MOVIE_REQUEST.getViewDto(),
                GET_ALL_MOVIE_REQUEST.getRequestDto(),
                new Object[]{rating == null ? "" : rating, price == null ? "" : price});
    }

    @RequestMapping(value = "/movie/search", method = RequestMethod.POST)
    @Request(type = GET_MOVIE_BY_SEARCH_REQUEST)
    public String findBySearchRequest(@RequestBody String request) {
        return requestService.handleListByJson(
                movieService::findBySearchRequest,
                GET_MOVIE_BY_SEARCH_REQUEST.getViewDto(),
                GET_MOVIE_BY_SEARCH_REQUEST.getRequestDto(),
                request
                );
    }
}
