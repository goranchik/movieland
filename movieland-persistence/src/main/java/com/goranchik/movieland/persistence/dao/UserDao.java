package com.goranchik.movieland.persistence.dao;

import com.goranchik.movieland.persistence.entity.User;

import java.util.List;

/**
 * Created by Ihor on 6/9/2016.
 */
public interface UserDao {
    User findById(int id);
    List<User> findAll();
    User findByEmailAndPassword(String email, String password);
}
