package com.goranchik.movieland.tools.dto.review;

import com.goranchik.movieland.tools.dto.RequestDto;
import lombok.Data;

/**
 * Created by Ihor on 7/2/2016.
 */
@Data
public class ReviewDto extends RequestDto {
    private int movieId;
    private String review;
}
