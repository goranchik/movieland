package com.goranchik.movieland.client.utils.converter;

/**
 * Created by Ihor on 6/9/2016.
 */
public interface EntityConverter<T, M> {
    String MOVIE_SINGLE_VIEW_CONVERTER = "movieSingleViewConverter";
    String MOVIE_MULTIPLE_VIEW_CONVERTER = "movieMultipleViewConverter";
    M convert(T entity);
}
