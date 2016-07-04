package com.goranchik.movieland.client.security;

import com.goranchik.movieland.persistence.entity.Principal;

/**
 * Created by Ihor on 7/3/2016.
 */
public class SecurityPrincipalHolder {
    private static final ThreadLocal<Principal> threadLocalScope = new  ThreadLocal<>();

    public final static Principal get() {
        return threadLocalScope.get();
    }

    public final static void set(Principal principal) {
        threadLocalScope.set(principal);
    }
}
