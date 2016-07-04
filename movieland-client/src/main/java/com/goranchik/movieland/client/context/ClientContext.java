package com.goranchik.movieland.client.context;

import com.goranchik.movieland.persistence.context.PersistenceContext;
import com.goranchik.movieland.tools.context.ToolsContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * Created by Ihor on 6/16/2016.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableScheduling
@ComponentScan("com.goranchik.movieland.client")
@Import({PersistenceContext.class, ToolsContext.class})
@PropertySource("classpath:cache.properties")
public class ClientContext implements SchedulingConfigurer {

    @Value("${cache.default.init.method}")
    private String cacheDefaultInitMethod;

    @Value("${cache.default.key}")
    private String cacheDefaultKey;

    @Value("${cache.principal.live.time.milis}")
    private String cachePrincipalLiveTime;

    @Bean
    public String getCacheDefaultInitMethod() {
        return cacheDefaultInitMethod;
    }

    @Bean
    public String getCacheDefaultKey() {
        return cacheDefaultKey;
    }

    @Bean
    public String getCachePrincipalLiveTime() {
        return cachePrincipalLiveTime;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar registrar) {
        registrar.setTaskScheduler(taskScheduler());
    }

    @Bean(name = "taskScheduler")
    public ThreadPoolTaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(2);
        scheduler.setWaitForTasksToCompleteOnShutdown(true);
        return scheduler;
    }
}
