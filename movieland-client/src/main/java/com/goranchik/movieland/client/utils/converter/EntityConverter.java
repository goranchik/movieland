package com.goranchik.movieland.client.utils.converter;

/**
 * Created by Ihor on 6/9/2016.
 */
public interface EntityConverter<T, M> {
    M convert(T entity);
}
