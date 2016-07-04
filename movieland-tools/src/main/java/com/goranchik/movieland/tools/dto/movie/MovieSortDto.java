package com.goranchik.movieland.tools.dto.movie;

import com.goranchik.movieland.tools.dto.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ihor on 6/18/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSortDto extends RequestDto {
    private String ratingOrder;
    private String priceOrder;
}
