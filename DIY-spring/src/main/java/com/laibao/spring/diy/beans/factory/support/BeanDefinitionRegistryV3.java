package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV3;

/**
 * 新增对Bean的各种类型[property,ref,value]属性值的处理
 *
 * 主要用于setter方式注入的时候获对象所包含的属性值列表
 */
public interface BeanDefinitionRegistryV3 {

    BeanDefinitionV3 getBeanDefinitionV3(String beanId);

    void registerBeanDefinitionV3(String beanId,BeanDefinitionV3 beanDefinition);
}
