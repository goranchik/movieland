package com.goranchik.movieland.service.cache;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Ihor on 6/22/2016.
 */
public interface CacheService {
    Object invoke(ProceedingJoinPoint jp);
    void init(Object obj, String cacheName);
    void evictSchedule(String cacheName);
 }
