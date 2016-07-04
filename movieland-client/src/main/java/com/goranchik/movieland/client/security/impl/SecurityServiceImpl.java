package com.goranchik.movieland.client.security.impl;

import com.goranchik.movieland.client.cache.CacheService;
import com.goranchik.movieland.client.security.SecurityPrincipalHolder;
import com.goranchik.movieland.client.security.SecurityService;
import com.goranchik.movieland.client.service.UserService;
import com.goranchik.movieland.persistence.entity.Principal;
import com.goranchik.movieland.persistence.entity.User;
import com.goranchik.movieland.tools.dto.RequestDto;
import com.goranchik.movieland.tools.dto.user.UserCredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static com.goranchik.movieland.tools.Constants.CACHE_PRINCIPAL;

/**
 * Created by Ihor on 7/3/2016.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserService userService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private String getCachePrincipalLiveTime;

    @Override
    public <T extends RequestDto> Principal authorize(T authDto) {
        String token = null;
        long liveTimeMilis = Long.parseLong(getCachePrincipalLiveTime);
        User user = userService.findByEmailAndPassword((UserCredentialsDto) authDto);
        if (user != null) {
            token = UUID.randomUUID().toString();
        }
        if (token != null) {
            Principal principal = new Principal(user, token, LocalDateTime.now().plusSeconds(liveTimeMilis / 1000));
            cacheService.add(CACHE_PRINCIPAL, token, principal, liveTimeMilis);
            scheduleUnAuthorize(liveTimeMilis);
            SecurityPrincipalHolder.set(principal);
        }
        return SecurityPrincipalHolder.get();
    }

    @Override
    public Principal getAuthorized(String token) {
        if (token != null) {
            return cacheService.get(CACHE_PRINCIPAL, token);
        }
        return null;
    }

    private void scheduleUnAuthorize(long liveTimeMilis){
        taskScheduler.schedule(
                () -> SecurityPrincipalHolder.set(null),
                new Date(System.currentTimeMillis() + liveTimeMilis));
    }
}
