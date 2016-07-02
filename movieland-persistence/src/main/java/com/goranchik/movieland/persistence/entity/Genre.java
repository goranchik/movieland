package com.goranchik.movieland.persistence.entity;

import com.goranchik.movieland.tools.annotation.CacheKey;
import com.goranchik.movieland.tools.Copyable;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ihor on 6/8/2016.
 */
@Data
@NoArgsConstructor
public class Genre extends Copyable {
    @CacheKey
    private int id;
    private String name;

    public Genre(int id) {
        this.id = id;
    }
}
