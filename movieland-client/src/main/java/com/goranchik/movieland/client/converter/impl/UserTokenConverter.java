package com.goranchik.movieland.client.converter.impl;

import com.goranchik.movieland.client.converter.EntityConverter;
import com.goranchik.movieland.persistence.entity.Principal;
import com.goranchik.movieland.tools.dto.user.view.UserTokenDto;
import org.springframework.stereotype.Service;

/**
 * Created by Ihor on 6/9/2016.
 */
@Service
public class UserTokenConverter implements EntityConverter<Principal, UserTokenDto> {

    @Override
    public UserTokenDto convert(Principal principal) {
        UserTokenDto dto = new UserTokenDto();
        dto.setToken(principal.getToken());
        return dto;
    }
}
