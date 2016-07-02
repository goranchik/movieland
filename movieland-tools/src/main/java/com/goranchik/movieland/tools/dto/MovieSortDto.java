package com.goranchik.movieland.tools.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Ihor on 6/18/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSortDto extends MovieRestDto{
    private String ratingOrder;
    private String priceOrder;
}
