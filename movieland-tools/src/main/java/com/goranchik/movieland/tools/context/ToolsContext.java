package com.goranchik.movieland.tools.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ihor on 6/8/2016.
 */
@Configuration
@ComponentScan({
        "com.goranchik.movieland.tools.utils"
})
public class ToolsContext {
}
