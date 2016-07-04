package com.goranchik.movieland.web;

import com.goranchik.movieland.client.context.ClientContext;
import com.goranchik.movieland.web.context.WebContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Ihor on 2/24/2016.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{ClientContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebContext.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
