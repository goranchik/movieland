package com.goranchik.movieland.tools;

/**
 * Created by Ihor on 6/24/2016.
 */
public class Copyable implements Cloneable {
    public Object copy(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Object cannot be cloned. " + e.getMessage());
        }
    }
}
