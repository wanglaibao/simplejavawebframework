package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV2;

public interface BeanDefinitionRegistryV2 {

    BeanDefinitionV2 getBeanDefinitionV2(String beanId);

    void registerBeanDefinitionV2(String beanId,BeanDefinitionV2 beanDefinition);
}
