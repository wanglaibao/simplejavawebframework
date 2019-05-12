package com.laibao.spring.diy.util;

public interface Assert {

    static void notNull(Object object,String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
