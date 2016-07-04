package com.goranchik.movieland.client.service.impl;

import com.goranchik.movieland.client.service.UserService;
import com.goranchik.movieland.persistence.dao.UserDao;
import com.goranchik.movieland.persistence.entity.User;
import com.goranchik.movieland.tools.dto.user.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ihor on 7/3/2016.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByEmailAndPassword(UserCredentialsDto authDto) {
        return userDao.findByEmailAndPassword(authDto.getLogin(), authDto.getPassword());
    }
}
