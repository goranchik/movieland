package com.goranchik.movieland.tools.dto.view;

import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data
public class MovieSingleViewDto extends MovieViewDto{
    private String title;
    private int yearOfRelease;
    private Set<String> countries;
    private Set<String> genres;
    private String description;
    private Map<String, String> reviews;
    private float rating;
}
