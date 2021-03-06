package com.goranchik.movieland.client.web;

import com.goranchik.movieland.client.context.ClientContext;
import com.goranchik.movieland.persistence.context.PersistenceContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Ihor on 2/24/2016.
 */
public class ClientWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{PersistenceContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{ClientContext.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


}
