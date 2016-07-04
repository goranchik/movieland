package com.goranchik.movieland.tools.dto.movie;

import com.goranchik.movieland.tools.dto.RequestDto;
import lombok.Data;

/**
 * Created by Ihor on 6/17/2016.
 */
@Data
public class MovieSearchDto extends RequestDto {
    private String genre;
    private String title;
    private String year;
    private String country;
}
