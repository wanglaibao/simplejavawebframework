package com.laibao.spring.diy.beans.factory;

import com.laibao.spring.diy.beans.BeanDefinition;

public interface BeanFactoryV1 {

    /**
     *
     * @param beanId
     * @return BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanId);


    /**
     *
     * @param beanId
     * @return Object
     */
    Object getBean(String beanId);

}
