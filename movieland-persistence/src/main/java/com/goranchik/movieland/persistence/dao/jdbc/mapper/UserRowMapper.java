package com.goranchik.movieland.persistence.dao.jdbc.mapper;


import com.goranchik.movieland.persistence.entity.User;
import com.goranchik.movieland.tools.enums.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.goranchik.movieland.tools.Constants.FIELD_ID;
import static com.goranchik.movieland.tools.enums.Table.USERS;

/**
 * Created by Ihor on 6/8/2016.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(FIELD_ID));
        user.setName(rs.getString(USERS.getFieldSet().get(0)));
        user.setEmail(rs.getString(USERS.getFieldSet().get(1)));
        user.setPassword(rs.getString(USERS.getFieldSet().get(2)));
        user.setRole(Role.getById(rs.getInt(USERS.getFieldSet().get(3))));
        return user;
    }
}
