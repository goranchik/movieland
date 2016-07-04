package com.goranchik.movieland.web.controller;

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
import static com.goranchik.movieland.tools.enums.RequestType.REMOVE_REVIEW_REQUEST;

/**
 * Created by Ihor on 6/8/2016.
 */
@RestController
@RequestMapping(value = "/v1/review", produces = MediaType.TEXT_PLAIN_VALUE)
public class ReviewContoller {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @Request(type = ADD_REVIEW_REQUEST)
    public String addReview(@RequestBody String addReviewRequest) {
        return requestService.handleOneByJson(
                reviewService::add,
                ADD_REVIEW_REQUEST.getViewDto(),
                ADD_REVIEW_REQUEST.getRequestDto(),
                addReviewRequest
        );
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    @Request(type = REMOVE_REVIEW_REQUEST)
    public String removeReview(@RequestBody String removeReviewRequest) {
        return requestService.handleOneByJson(
                reviewService::remove,
                REMOVE_REVIEW_REQUEST.getViewDto(),
                REMOVE_REVIEW_REQUEST.getRequestDto(),
                removeReviewRequest
        );
    }

}
