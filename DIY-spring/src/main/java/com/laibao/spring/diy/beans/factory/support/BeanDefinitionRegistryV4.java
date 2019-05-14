package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV3;
import com.laibao.spring.diy.beans.BeanDefinitionV4;

/**
 * 新增对构造函数参数的支持
 *
 * 主要用于构造函数方式注入的时候
 *
 * 获对象的构造函数参数列表所包含的参数值
 */
public interface BeanDefinitionRegistryV4 {

    BeanDefinitionV4 getBeanDefinitionV4(String beanId);

    void registerBeanDefinitionV4(String beanId,BeanDefinitionV4 beanDefinition);
}
