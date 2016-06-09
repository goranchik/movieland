package com.goranchik.movieland.persistence.entity;

import lombok.Data;

import java.util.Set;

/**
 * Created by Ihor on 6/8/2016.
 */
@Data
public class Movie {
    private int id;
    private String name;
    private String nameOriginal;
    private int year;
    private String description;
    private float rating;
    private float price;
    private Set<Genre> genres;
    private Set<Country> countries;
}
