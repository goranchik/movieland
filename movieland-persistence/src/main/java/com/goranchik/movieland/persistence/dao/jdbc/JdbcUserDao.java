package com.goranchik.movieland.persistence.dao.jdbc;

import com.goranchik.movieland.persistence.dao.UserDao;
import com.goranchik.movieland.persistence.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ihor on 6/9/2016.
 */
@Repository
public class JdbcUserDao implements UserDao {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private BeanPropertyRowMapper<User> mapper = new BeanPropertyRowMapper<>(User.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private String findUserByIdSQL;

    @Autowired
    private String findAllUsersSQL;

    @Override
    public User findById(int id) {
        log.info("Start query to find user by id {} from DB", id);
        long startTime = System.currentTimeMillis();
        User user = jdbcTemplate.queryForObject(findUserByIdSQL, new Object[]{id}, mapper);
        log.info("Finish query to find user by id {} from DB. It took {} ms", id, System.currentTimeMillis() - startTime);
        return user;
    }

    @Override
    public List<User> findAll() {
        log.info("Start query to find all users from DB");
        long startTime = System.currentTimeMillis();
        List<User> users = jdbcTemplate.query(findAllUsersSQL, mapper);
        log.info("Finish query to find all users from DB. It took {} ms", System.currentTimeMillis() - startTime);
        return users;
    }


}
