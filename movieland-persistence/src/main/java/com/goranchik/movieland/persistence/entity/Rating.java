package com.goranchik.movieland.persistence.entity;

import lombok.Data;

/**
 * Created by Ihor on 7/4/2016.
 */
@Data
public class Rating {
    private User user;
    private Movie movie;
    private int rating;
}
