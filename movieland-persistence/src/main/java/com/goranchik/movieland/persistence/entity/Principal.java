package com.goranchik.movieland.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created by Ihor on 7/3/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Principal {
    private User user;
    private String token;
    private LocalDateTime expTime;
}
