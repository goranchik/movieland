package com.goranchik.movieland.tools.dto.movie.view;

import com.goranchik.movieland.tools.dto.ViewDto;
import lombok.Data;

import java.util.Map;
import java.util.Set;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data
public class MovieSingleDto extends ViewDto {
    private String title;
    private int yearOfRelease;
    private Set<String> countries;
    private Set<String> genres;
    private String description;
    private Map<String, String> reviews;
    private float rating;
}
