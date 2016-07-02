package com.goranchik.movieland.service.cache.impl;

import com.goranchik.movieland.service.cache.CacheService;
import com.goranchik.movieland.tools.annotation.CacheInit;
import com.goranchik.movieland.tools.annotation.CacheKey;
import com.goranchik.movieland.tools.annotation.Cacheable;
import com.goranchik.movieland.tools.enums.Table;
import com.goranchik.movieland.tools.Copyable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by Ihor on 6/21/2016.
 */
@Service
public class CacheServiceImpl implements CacheService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private String getCacheDefaultInitMethod;

    @Autowired
    private String getCacheDefaultKey;

    @Autowired
    private TaskScheduler taskScheduler;

    private Map<String, Map<Object, Object>> cache = new ConcurrentHashMap<>();

    @Override
    public Object invoke(ProceedingJoinPoint jp) {
        Method method = Arrays.stream(jp.getTarget().getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Cacheable.class))
                .filter(m -> m.getName().equals(jp.getSignature().getName()))
                .findFirst().orElse(null);

        String cacheName = method.getAnnotation(Cacheable.class).table().getName();

        if (!cache.containsKey(cacheName)){
            evictSchedule(cacheName);
        }

        if (!cache.containsKey(cacheName)
                || cache.get(cacheName).isEmpty()) {
            init(jp.getTarget(), cacheName);
        }

        Object key = Arrays.stream(jp.getArgs()).findFirst().orElse(null);
        if (!cache.get(cacheName).containsKey(key)) {
            try {
                cache.get(cacheName).put(key, method.invoke(jp.getTarget(), key));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return ((Copyable) cache.get(cacheName).get(key)).copy();
    }

    @Override
    public void init(Object obj, String cacheName) {
        try {
            Method method = obj.getClass().getDeclaredMethod(getCacheDefaultInitMethod);
            method = Arrays.stream(obj.getClass().getDeclaredMethods())
                    .filter(f ->
                            f.isAnnotationPresent(CacheInit.class)
                                    && f.getAnnotation(CacheInit.class).table().getName().equals(cacheName))
                    .findFirst().orElse(method);

            cache.put(cacheName, ((Collection<Object>) method.invoke(obj))
                    .stream().collect(Collectors
                            .toMap(e -> {
                                Field[] fields = e.getClass().getDeclaredFields();
                                Field field = null;
                                try {
                                    field = e.getClass().getDeclaredField(getCacheDefaultKey);
                                } catch (NoSuchFieldException e1) {
                                    e1.printStackTrace();
                                }
                                field = Arrays.asList(fields)
                                        .stream().filter(f ->
                                                f.isAnnotationPresent(CacheKey.class)).findFirst().orElse(field);
                                field.setAccessible(true);
                                try {
                                    return field.get(e);
                                } catch (IllegalAccessException e1) {
                                    e1.printStackTrace();
                                } finally {
                                    field.setAccessible(false);
                                }
                                return null;
                            }, e -> e)));
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void evictSchedule(String cacheName) {
        Runnable evictTask = new Runnable() {
            @Override
            public void run() {
                evictDo();
            }

            private void evictDo() {
                if (cache.containsKey(cacheName)) {
                    cache.get(cacheName).clear();
                    log.info("Cache {} was cleared!", cacheName);
                }
            }
        };
        taskScheduler.schedule(evictTask,
                new CronTrigger(Table.valueOf(cacheName.toUpperCase()).getCacheEvictCron()));
    }
}

