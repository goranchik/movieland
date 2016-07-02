package com.goranchik.movieland.tools.enums;

import com.goranchik.movieland.tools.dto.*;
import com.goranchik.movieland.tools.dto.view.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.goranchik.movieland.tools.enums.Role.*;

/**
 * Created by Ihor on 6/28/2016.
 */
@Getter
@AllArgsConstructor
public enum RequestType {
    GET_ALL_MOVIE_REQUEST("getAllMovieRequest", GUEST, MovieMultipleViewDto.class, MovieSortDto.class),
    GET_MOVIE_BY_ID_REQUEST("getMovieByIdRequest", GUEST, MovieSingleViewDto.class),
    GET_MOVIE_BY_SEARCH_REQUEST("getMovieBySearchRequest", GUEST, MovieMultipleViewDto.class, MovieSearchDto.class),
    GET_OWN_MOVIE_RATING("getOwnMovieRating"),
    GET_MOVIE_POSTER("getMoviePoster"),
    RATE_MOVIE_REQUEST("rateMovieRequest", USER),
    ADD_REVIEW_REQUEST("addReviewRequest", USER),
    REMOVE_REVIEW_REQUEST("removeReviewRequest", ADMIN),
    ADD_MOVIE_REQUEST("addMovieRequest", ADMIN),
    EDIT_MOVIE_REQUEST("editMovieRequest", ADMIN),
    MARK_MOVIE_FOR_REMOVING_REQUEST("markMovieForRemovingRequest", ADMIN),
    UNMARK_MOVIE_FOR_REMOVING_REQUEST("unmarkMovieForRemovingRequest", ADMIN);

    private String type;
    private Role role = GUEST;
    private Class<? extends MovieViewDto> viewDto;
    private Class<? extends MovieRestDto> requestDto;

    RequestType(String type) {
        this.type = type;
    }

    RequestType(String type, Role role) {
        this.type = type;
        this.role = role;
    }

    RequestType(String type, Role role, Class<? extends MovieViewDto> viewDto) {
        this.type = type;
        this.role = role;
        this.viewDto = viewDto;
    }
}
