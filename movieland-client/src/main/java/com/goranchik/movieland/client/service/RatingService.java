package com.goranchik.movieland.client.service;

import com.goranchik.movieland.persistence.entity.Rating;
import com.goranchik.movieland.tools.dto.RequestDto;

/**
 * Created by Ihor on 6/13/2016.
 */
public interface RatingService {
    <T extends RequestDto> Rating add(T ratingDto);
}
