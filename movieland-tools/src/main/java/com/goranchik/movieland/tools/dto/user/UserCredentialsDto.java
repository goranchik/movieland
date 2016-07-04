package com.goranchik.movieland.tools.dto.user;

import com.goranchik.movieland.tools.dto.RequestDto;
import lombok.Data;

/**
 * Created by Ihor on 7/2/2016.
 */
@Data
public class UserCredentialsDto extends RequestDto {
    private String login;
    private String password;
}
