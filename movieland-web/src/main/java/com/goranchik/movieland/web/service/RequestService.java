package com.goranchik.movieland.web.service;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Ihor on 6/28/2016.
 */

public interface RequestService{
    <D, V, E> String handleOneByParams(Function<D, E> function,
                                    Class<V> viewDtoClazz,
                                    Class<D> dtoClazz,
                                    Object[] params);

    <D, V, E> String handleListByParams(Function<D, List<E>> function,
                                     Class<V> viewDtoClazz,
                                     Class<D> dtoClazz,
                                     Object[] params);

    <D, V, E> String handleListByJson(Function<D, List<E>> function,
                                   Class<V> viewDtoClazz,
                                   Class<D> dtoClazz,
                                   String json);

    <D, V, E> String handleOneByJson(Function<D, E> function,
                                   Class<V> viewDtoClazz,
                                   Class<D> dtoClazz,
                                   String json);
    Cookie handleCookie();
}
