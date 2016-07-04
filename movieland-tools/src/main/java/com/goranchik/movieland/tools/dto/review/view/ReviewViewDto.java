package com.goranchik.movieland.tools.dto.review.view;

import com.goranchik.movieland.tools.dto.ViewDto;
import lombok.Data;

/**
 * Created by Ihor on 7/2/2016.
 */
@Data
public class ReviewViewDto extends ViewDto {
    private int authorId;
    private int movieId;
    private String review;
}
