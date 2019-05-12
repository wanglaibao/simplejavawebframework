package com.laibao.spring.diy.beans.factory.config;

/**
 * 用于setter方式的注入
 */
public class TypedStringValue {

    private String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
