package com.goranchik.movieland.client.security;

import com.goranchik.movieland.persistence.entity.Principal;
import com.goranchik.movieland.tools.dto.RequestDto;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface SecurityService {
    <T extends RequestDto> Principal authorize(T authDto);
    Principal getAuthorized(String token);
}

