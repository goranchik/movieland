package com.goranchik.movieland.client.web.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data
public class MovieMultipleViewDto {
    private String title;
    private int yearOfRelease;
    private float rating;
    private Set<String> genres;
}
