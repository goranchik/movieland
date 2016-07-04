package com.goranchik.movieland.client.service;

import com.goranchik.movieland.persistence.entity.User;
import com.goranchik.movieland.tools.dto.user.UserCredentialsDto;

/**
 * Created by Ihor on 6/8/2016.
 */
public interface UserService {
    User findByEmailAndPassword(UserCredentialsDto authDto);
}

