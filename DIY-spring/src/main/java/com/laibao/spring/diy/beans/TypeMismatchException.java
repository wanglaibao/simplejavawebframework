package com.laibao.spring.diy.beans;

public class TypeMismatchException extends BeanException{

    private transient Object value;

    private Class<?> requiredType;


    public TypeMismatchException(String message) {
        super(message);
    }

    public TypeMismatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value:" + value + "to type" + requiredType);
        this.value = value;
        this.requiredType = requiredType;

    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
