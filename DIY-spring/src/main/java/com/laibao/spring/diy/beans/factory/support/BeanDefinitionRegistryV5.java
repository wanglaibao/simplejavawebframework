package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV4;
import com.laibao.spring.diy.beans.BeanDefinitionV5;

public interface BeanDefinitionRegistryV5 {
    BeanDefinitionV5 getBeanDefinitionV5(String beanId);

    void registerBeanDefinitionV5(String beanId,BeanDefinitionV5 beanDefinition);
}
