package com.goranchik.movieland.web.service.impl;


import com.goranchik.movieland.client.converter.impl.Converter;
import com.goranchik.movieland.client.security.SecurityPrincipalHolder;
import com.goranchik.movieland.persistence.entity.Principal;
import com.goranchik.movieland.tools.utils.JsonJacksonConverter;
import com.goranchik.movieland.web.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.goranchik.movieland.tools.Constants.COOKIE_PATH;
import static com.goranchik.movieland.tools.Constants.COOKIE_TOKEN;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * Created by Ihor on 6/28/2016.
 */
@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private JsonJacksonConverter jsonConverter;

    @Autowired
    private Converter converter;

    @Override
    public <D, V, E> String handleOneByParams(Function<D, E> function,
                                              Class<V> viewDtoClazz,
                                              Class<D> dtoClazz,
                                              Object[] params) {
        D dto = getDtoParams(dtoClazz, params);
        return jsonConverter.toJson(
                converter.convert(
                        function.apply(dto), viewDtoClazz));
    }

    @Override
    public <D, V, E> String handleListByParams(Function<D, List<E>> function,
                                               Class<V> viewDtoClazz,
                                               Class<D> dtoClazz,
                                               Object[] params) {
        D dto = getDtoParams(dtoClazz, params);
        return jsonConverter.toJson(
                function.apply(dto).stream().map(movie ->
                        converter.convert(movie, viewDtoClazz)).collect(Collectors.toList())
        );
    }

    @Override
    public <D, V, E> String handleListByJson(Function<D, List<E>> function,
                                             Class<V> viewDtoClazz,
                                             Class<D> dtoClazz,
                                             String json) {
        return jsonConverter.toJson(
                function.apply(jsonConverter.jsonToObj(json, dtoClazz))
                        .stream().map((movie) ->
                        converter.convert(movie, viewDtoClazz)).collect(Collectors.toList())
        );
    }

    @Override
    public <D, V, E> String handleOneByJson(Function<D, E> function,
                                            Class<V> viewDtoClazz,
                                            Class<D> dtoClazz,
                                            String json) {
        return jsonConverter.toJson(
                converter.convert(
                        function.apply(jsonConverter.jsonToObj(json, dtoClazz)),
                        viewDtoClazz));
    }

    @Override
    public Cookie handleCookie() {
        Principal principal = SecurityPrincipalHolder.get();
        if (principal != null) {
            Cookie cookie = new Cookie(COOKIE_TOKEN, principal.getToken());
            cookie.setMaxAge((int) LocalDateTime.now().until(principal.getExpTime(), SECONDS));
            cookie.setPath(COOKIE_PATH);
            return cookie;
        }
        return null;
    }

    private <D> D getDtoParams(Class<D> dtoClazz,
                               Object[] params) {
        int length = params.length;
        D dto = null;
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
