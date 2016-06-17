package com.goranchik.movieland.tools.dto;

import lombok.Data;

/**
 * Created by Ihor on 6/17/2016.
 */
@Data
public class MovieSearchRequestDto {
    private String genre;
    private String title;
    private String year;
    private String country;
}
