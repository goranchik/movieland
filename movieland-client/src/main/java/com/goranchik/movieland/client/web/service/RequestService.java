package com.goranchik.movieland.client.web.service;

import com.goranchik.movieland.persistence.entity.Movie;

import java.util.List;
import java.util.function.Function;

/**
 * Created by Ihor on 6/28/2016.
 */

public interface RequestService{
    <T, R> String handleOneByParams(Function<T, Movie> function,
                             Class<R> viewDtoClazz,
                             Class<T> dtoClazz,
                             Object[] params);

    <T, R> String handleListByParams(Function<T, List<Movie>> function,
                              Class<R> viewDtoClazz,
                              Class<T> dtoClazz,
                              Object[] params);

    <T, R> String handleListByJson(Function<T, List<Movie>> function,
                            Class<R> viewDtoClazz,
                            Class<T> dtoClazz,
                            String json);
}
