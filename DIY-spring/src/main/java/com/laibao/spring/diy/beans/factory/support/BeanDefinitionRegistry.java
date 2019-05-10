package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinition;

public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String beanId);

    void registerBeanDefinition(String beanId,BeanDefinition beanDefinition);
}
