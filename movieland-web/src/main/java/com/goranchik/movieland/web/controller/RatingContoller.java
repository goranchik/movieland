package com.goranchik.movieland.web.controller;

import com.goranchik.movieland.client.service.RatingService;
import com.goranchik.movieland.client.service.ReviewService;
import com.goranchik.movieland.tools.annotation.Request;
import com.goranchik.movieland.web.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.goranchik.movieland.tools.enums.RequestType.ADD_REVIEW_REQUEST;
import static com.goranchik.movieland.tools.enums.RequestType.RATE_MOVIE_REQUEST;
import static com.goranchik.movieland.tools.enums.RequestType.REMOVE_REVIEW_REQUEST;

/**
 * Created by Ihor on 6/8/2016.
 */
@RestController
@RequestMapping(value = "/v1/rating", produces = MediaType.TEXT_PLAIN_VALUE)
public class RatingContoller {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Request(type = RATE_MOVIE_REQUEST)
    public String addReview(@RequestBody String ratingRequest) {
        return requestService.handleOneByJson(
                ratingService::add,
                RATE_MOVIE_REQUEST.getViewDto(),
                RATE_MOVIE_REQUEST.getRequestDto(),
                ratingRequest
        );
    }

}
