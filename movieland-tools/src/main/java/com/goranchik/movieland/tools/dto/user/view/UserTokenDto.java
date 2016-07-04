package com.goranchik.movieland.tools.dto.user.view;

import com.goranchik.movieland.tools.dto.ViewDto;
import lombok.Data;

/**
 * Created by Ihor on 7/2/2016.
 */
@Data
public class UserTokenDto extends ViewDto {
    private String token;
}
