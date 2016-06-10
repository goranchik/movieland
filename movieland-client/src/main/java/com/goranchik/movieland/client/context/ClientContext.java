package com.goranchik.movieland.client.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Ihor on 6/8/2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan({
        "com.goranchik.movieland.client.web.controller",
        "com.goranchik.movieland.client.web.service",
        "com.goranchik.movieland.client.utils"
})
public class ClientContext extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(31556926);
    }
}
