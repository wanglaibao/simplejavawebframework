package com.laibao.spring.diy.beans;

public class BeanException extends RuntimeException{

    public BeanException(String message) {
        super(message);
    }

    public BeanException(String message, Throwable cause) {
        super(message, cause);
    }
}
