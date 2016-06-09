package com.goranchik.movieland.persistence.utils;

/**
 * Created by Ihor on 6/7/2016.
 */
public enum Table {
    GENRE,
    USERS,
    MOVIE,
    COUNTRY,
    MOVIE_COUNTRY,
    MOVIE_GENRE,
    REVIEW;

    public String fields(){
        return this.name().toLowerCase() + ".fields";
    }
}
