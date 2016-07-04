package com.goranchik.movieland.client.advice;

import com.goranchik.movieland.client.cache.impl.CacheServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ihor on 6/21/2016.
 */
@Aspect
@Component
public class CacheAdvice {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CacheServiceImpl cacheService;

    @Around("@annotation(com.goranchik.movieland.tools.annotation.Cacheable)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) {
        Object returnObject;
        log.info("CacheAspect body is now executed Before method is called.");
        returnObject = cacheService.invoke(joinPoint);
        log.info("CacheAspect body is now executed After method is called.");
        return returnObject;
    }
}
