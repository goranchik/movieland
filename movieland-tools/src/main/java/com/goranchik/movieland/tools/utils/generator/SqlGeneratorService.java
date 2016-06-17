package com.goranchik.movieland.tools.utils.generator;

/**
 * Created by Ihor on 6/17/2016.
 */
public interface SqlGeneratorService<T> {
    String getSearchSql(T searchDto);
}
