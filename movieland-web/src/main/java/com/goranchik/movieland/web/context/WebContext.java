package com.goranchik.movieland.web.context;


import com.goranchik.movieland.web.interseptors.SecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by Ihor on 6/8/2016.
 */
@Configuration
@EnableWebMvc
@ComponentScan({
        "com.goranchik.movieland.web.controller",
        "com.goranchik.movieland.web.service",
        "com.goranchik.movieland.web.interseptors"
})
public class WebContext extends WebMvcConfigurerAdapter {

    @Autowired
    private SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(securityInterceptor).addPathPatterns("/v1/**");
    }
}
