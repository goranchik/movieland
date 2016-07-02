package com.goranchik.movieland.tools.dto.view;

import lombok.Data;

import java.util.Set;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data
public class MovieMultipleViewDto extends MovieViewDto {
    private String title;
    private int yearOfRelease;
    private float rating;
    private Set<String> genres;
}
