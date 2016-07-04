package com.goranchik.movieland.persistence.entity;

import com.goranchik.movieland.tools.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data @NoArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private Role role;

    public User(int id){
        this.id = id;
    }
}
