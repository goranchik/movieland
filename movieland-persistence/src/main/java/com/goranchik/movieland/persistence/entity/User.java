package com.goranchik.movieland.persistence.entity;

import lombok.Data;

/**
 * Created by Ihor on 6/9/2016.
 */
@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
}
