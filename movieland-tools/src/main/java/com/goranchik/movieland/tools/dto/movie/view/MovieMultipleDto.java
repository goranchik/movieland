package com.goranchik.movieland.tools.dto.movie.view;

import com.goranchik.movieland.tools.dto.ViewDto;
import lombok.Data;

import java.util.Set;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data
public class MovieMultipleDto extends ViewDto {
    private String title;
    private int yearOfRelease;
    private float rating;
    private Set<String> genres;
}
