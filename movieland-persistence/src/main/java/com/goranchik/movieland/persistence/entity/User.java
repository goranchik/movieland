package com.goranchik.movieland.persistence.entity;

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

    public User(int id){
        this.id = id;
    }
}
