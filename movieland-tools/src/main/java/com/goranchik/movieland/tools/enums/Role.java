package com.goranchik.movieland.tools.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

/**
 * Created by Ihor on 6/28/2016.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum Role {
    GUEST(0, "guest"),
    USER(1, "user"),
    ADMIN(2, "admin");

    private int id;
    private String name;

    public static Role getById(int id) {
        return Arrays.stream(Role.values())
                .filter(role -> role.getId() == id).findFirst().orElse(GUEST);
    }
}
