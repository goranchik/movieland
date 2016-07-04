package com.goranchik.movieland.client.service;

import com.goranchik.movieland.persistence.entity.Review;
import com.goranchik.movieland.tools.dto.RequestDto;
import com.goranchik.movieland.tools.dto.ViewDto;

import java.util.List;

/**
 * Created by Ihor on 6/13/2016.
 */
public interface ReviewService {
    List<Review> findAll();
    List<Review> findByMovieId(int id);
    <T extends RequestDto> Review add(T addReviewDto);
    <T extends RequestDto> Review remove(T removeReviewDto);
}
