package com.goranchik.movieland.client.service.impl;

import com.goranchik.movieland.client.service.RatingService;
import com.goranchik.movieland.persistence.entity.Rating;
import com.goranchik.movieland.tools.dto.RequestDto;
import org.springframework.stereotype.Service;

/**
 * Created by Ihor on 7/4/2016.
 */
@Service
public class RatingServiceImpl implements RatingService {
    @Override
    public <T extends RequestDto> Rating add(T ratingDto) {
        return null;
    }
}
