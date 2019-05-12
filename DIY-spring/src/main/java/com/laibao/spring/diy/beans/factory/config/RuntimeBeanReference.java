package com.laibao.spring.diy.beans.factory.config;

/**
 * 用于setter方式的注入
 */
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
