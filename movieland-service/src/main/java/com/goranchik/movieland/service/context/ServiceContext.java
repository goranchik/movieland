package com.goranchik.movieland.service.context;

import com.goranchik.movieland.persistence.context.PersistenceContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Ihor on 6/16/2016.
 */
@Configuration
@ComponentScan({
        "com.goranchik.movieland.service"
})
@Import(PersistenceContext.class)
public class ServiceContext {
}
