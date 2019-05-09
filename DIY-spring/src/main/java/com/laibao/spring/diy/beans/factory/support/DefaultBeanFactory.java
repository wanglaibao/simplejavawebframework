package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.factory.BeanFactory;

public class DefaultBeanFactory implements BeanFactory {

    public DefaultBeanFactory(String configFile) {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return null;
    }

    @Override
    public Object getBean(String beanId) {
        return null;
    }
}
