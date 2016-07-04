package com.goranchik.movieland.tools.enums;

import com.goranchik.movieland.tools.dto.RequestDto;
import com.goranchik.movieland.tools.dto.ViewDto;
import com.goranchik.movieland.tools.dto.movie.MovieSearchDto;
import com.goranchik.movieland.tools.dto.movie.MovieSortDto;
import com.goranchik.movieland.tools.dto.movie.view.MovieMultipleDto;
import com.goranchik.movieland.tools.dto.movie.view.MovieSingleDto;
import com.goranchik.movieland.tools.dto.review.ReviewDto;
import com.goranchik.movieland.tools.dto.review.view.ReviewViewDto;
import com.goranchik.movieland.tools.dto.user.UserCredentialsDto;
import com.goranchik.movieland.tools.dto.user.view.UserTokenDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.goranchik.movieland.tools.enums.Role.*;

/**
 * Created by Ihor on 6/28/2016.
 */
@Getter
@AllArgsConstructor
public enum RequestType {
    GET_ALL_MOVIE_REQUEST("getAllMovieRequest",
            new Role[]{GUEST, USER, ADMIN},
            MovieMultipleDto.class,
            MovieSortDto.class),
    GET_MOVIE_BY_ID_REQUEST("getMovieByIdRequest",
            new Role[]{GUEST, USER, ADMIN},
            MovieSingleDto.class),
    GET_MOVIE_BY_SEARCH_REQUEST("getMovieBySearchRequest",
            new Role[]{GUEST, USER, ADMIN},
            MovieMultipleDto.class,
            MovieSearchDto.class),
    AUTH_REQUEST("authorizeRequest",
            new Role[]{GUEST},
            UserTokenDto.class,
            UserCredentialsDto.class),
    ADD_REVIEW_REQUEST("addReviewRequest",
            new Role[]{USER},
            ReviewViewDto.class,
            ReviewDto.class
            ),
    REMOVE_REVIEW_REQUEST("removeReviewRequest",
            new Role[]{ADMIN}),
    RATE_MOVIE_REQUEST("rateMovieRequest",
            new Role[]{USER}),
    GET_OWN_MOVIE_RATING("getOwnMovieRating"),
    GET_MOVIE_POSTER("getMoviePoster"),
    ADD_MOVIE_REQUEST("addMovieRequest",
            new Role[]{ADMIN}),
    EDIT_MOVIE_REQUEST("editMovieRequest",
            new Role[]{ADMIN}),
    MARK_MOVIE_FOR_REMOVING_REQUEST("markMovieForRemovingRequest",
            new Role[]{ADMIN}),
    UNMARK_MOVIE_FOR_REMOVING_REQUEST("unmarkMovieForRemovingRequest",
            new Role[]{ADMIN});

    private String type;
    private Role[] roles = {GUEST};
    private Class<? extends ViewDto> viewDto;
    private Class<? extends RequestDto> requestDto;

    RequestType(String type) {
        this.type = type;
    }

    RequestType(String type, Role[] roles) {
        this.type = type;
        this.roles = roles;
    }

    RequestType(String type, Role[] roles, Class<? extends ViewDto> viewDto) {
        this.type = type;
        this.roles = roles;
        this.viewDto = viewDto;
    }
}
