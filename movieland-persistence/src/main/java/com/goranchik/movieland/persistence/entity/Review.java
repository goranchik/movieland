package com.goranchik.movieland.persistence.entity;

import lombok.Data;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data
public class Review {
    private Movie movie;
    private User reviewer;
    private String feedback;
}
