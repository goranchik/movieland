package com.goranchik.movieland.client.cache;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created by Ihor on 6/22/2016.
 */
public interface CacheService {
    Object invoke(ProceedingJoinPoint jp);
    void init(Object obj, String cacheName);
    <K, V> V add(String cacheName, K cacheKey, V cacheValue, long liveTime);
    <K, V> V get(String cacheName, K cacheKey);
    void addScheduleEvictCache(String cacheName);
    <K> void addScheduleEvict(String cacheName, K cacheKey, long liveTime);
}
