package com.goranchik.movieland.client.converter.impl;

import com.goranchik.movieland.client.converter.EntityConverter;
import com.goranchik.movieland.persistence.entity.Movie;
import com.goranchik.movieland.persistence.entity.Principal;
import com.goranchik.movieland.tools.dto.movie.view.MovieMultipleDto;
import com.goranchik.movieland.tools.dto.movie.view.MovieSingleDto;
import com.goranchik.movieland.tools.dto.user.view.UserTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class ViewConverter {

    @Autowired
    private EntityConverter<Movie, MovieSingleDto> singleConverter;

    @Autowired
    private EntityConverter<Movie, MovieMultipleDto> multipleConverter;

    @Autowired
    private EntityConverter<Principal, UserTokenDto> userTokenConverter;


    public <T, E> T convert(E entity, Class<T> clazz) {
        if (entity.getClass().equals(Movie.class)){
            if (clazz.equals(MovieSingleDto.class)){
                return (T) singleConverter.convert((Movie) entity);
            } else if (clazz.equals(MovieMultipleDto.class)){
                return (T) multipleConverter.convert((Movie) entity);
            }
        } else if (entity.getClass().equals(Principal.class)){
            if (clazz.equals(UserTokenDto.class)){
                return (T) userTokenConverter.convert((Principal) entity);
            }
        }
        return null;
    }
}
