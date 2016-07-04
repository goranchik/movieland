package com.goranchik.movieland.persistence.entity;

import com.goranchik.movieland.tools.Copyable;
import com.goranchik.movieland.tools.annotation.CacheKey;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ihor on 6/8/2016.
 */
@Data
@NoArgsConstructor
public class Country extends Copyable {
    @CacheKey
    private int id;
    private String name;
    public Country(int id) {
        this.id = id;
    }
}
