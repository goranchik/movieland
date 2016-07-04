package com.goranchik.movieland.web.interseptors;

import com.goranchik.movieland.client.security.SecurityPrincipalHolder;
import com.goranchik.movieland.client.security.SecurityService;
import com.goranchik.movieland.tools.annotation.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

import static com.goranchik.movieland.tools.Constants.COOKIE_TOKEN;

/**
 * Created by Ihor on 7/3/2016.
 */
@Service
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private SecurityService securityService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = null;
        if (request.getCookies() != null) {
            token = Arrays.stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals(COOKIE_TOKEN))
                    .map(Cookie::getValue).findFirst().orElse(null);
        }
        if (token == null) {
            token = request.getHeader(COOKIE_TOKEN);
        }
        if (SecurityPrincipalHolder.get() == null && token != null) {
            SecurityPrincipalHolder.set(securityService.getAuthorized(token));
        }

        Method method = ((HandlerMethod) handler).getMethod();
        if (method.isAnnotationPresent(Request.class)) {
            if (!Arrays.stream(method.getAnnotation(Request.class).type().getRoles())
                    .anyMatch(role -> role.equals(SecurityPrincipalHolder.get().getUser().getRole()))) {
                throw new RuntimeException("To submit request "
                        + method.getAnnotation(Request.class).type()
                        + " you need to have one of the next role: "
                        + Arrays.toString(method.getAnnotation(Request.class).type().getRoles())
                );
            }
        }
        return super.preHandle(request, response, handler);
    }

}
