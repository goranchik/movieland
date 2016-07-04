package com.goranchik.movieland.web.controller;

import com.goranchik.movieland.client.security.SecurityService;
import com.goranchik.movieland.tools.annotation.Request;
import com.goranchik.movieland.web.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.goranchik.movieland.tools.enums.RequestType.AUTH_REQUEST;

/**
 * Created by Ihor on 6/8/2016.
 */
@RestController
@RequestMapping(value = "/v1", produces = MediaType.TEXT_PLAIN_VALUE)
public class UserContoller {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RequestService requestService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @Request(type = AUTH_REQUEST)
    public String authRequest(@RequestBody String authRequest, HttpServletResponse response) {
        String json = requestService.handleOneByJson(
                securityService::authorize,
                AUTH_REQUEST.getViewDto(),
                AUTH_REQUEST.getRequestDto(),
                authRequest
        );
        response.addCookie(requestService.handleCookie());
        return json;
    }
}
