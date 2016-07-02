package com.goranchik.movieland.client.web.service.impl;

import com.goranchik.movieland.client.utils.converter.JsonJacksonConverter;
import com.goranchik.movieland.client.utils.converter.impl.MovieViewConverter;
import com.goranchik.movieland.client.web.service.RequestService;
import com.goranchik.movieland.persistence.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by Ihor on 6/28/2016.
 */
@Service
public class RequestServiceImpl implements RequestService{

    @Autowired
    private JsonJacksonConverter jsonConverter;

    @Autowired
    private MovieViewConverter viewConverter;

    @Override
    public <T, R> String handleOneByParams(Function<T, Movie> function,
                                    Class<R> viewDtoClazz,
                                    Class<T> dtoClazz,
                                    Object[] params) {
        T dto = getDtoParams(dtoClazz, params);
        return jsonConverter.toJson(
                viewConverter.convert(
                        function.apply(dto), viewDtoClazz));
    }

    @Override
    public <T, R> String handleListByParams(Function<T, List<Movie>> function,
                                            Class<R> viewDtoClazz,
                                            Class<T> dtoClazz,
                                            Object[] params) {
        T dto = getDtoParams(dtoClazz, params);
        return jsonConverter.toJson(
                function.apply(dto).stream().map(movie ->
                        viewConverter.convert(movie, viewDtoClazz)).collect(Collectors.toList())
                );
    }

    @Override
    public <T, R> String handleListByJson(Function<T, List<Movie>> function,
                                   Class<R> viewDtoClazz,
                                   Class<T> dtoClazz,
                                   String json) {
        return jsonConverter.toJson(
                function.apply(jsonConverter.jsonToObj(json, dtoClazz))
                        .stream().map((movie) ->
                        viewConverter.convert(movie, viewDtoClazz)).collect(Collectors.toList())
        );
    }

    private <T> T getDtoParams(Class<T> dtoClazz,
                               Object[] params){
        int length = params.length;
        T dto = null;
        Class[] paramsClazz = new Class[length];
        for (int i = 0; i < length; i++) {
            paramsClazz[i] = params[i].getClass() == Integer.class ? int.class : params[i].getClass();
        }
        try {
            dto = dtoClazz.getDeclaredConstructor(paramsClazz).newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
