package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.factory.BeanFactoryV2;

public class CommonBeanDefinitionValueResolver extends BeanDefinitionValueResolverTemplate{

    private final BeanFactoryV2 factory;

    public CommonBeanDefinitionValueResolver(BeanFactoryV2 factory) {
        this.factory = factory;
    }

    @Override
    protected Object getBeanByName(String beanName) {
        Object bean = factory.getBean(beanName);
        return bean;
    }
}
