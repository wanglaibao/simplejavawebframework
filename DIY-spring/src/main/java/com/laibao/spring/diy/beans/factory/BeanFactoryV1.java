package com.laibao.spring.diy.beans.factory;

import com.laibao.spring.diy.beans.BeanDefinition;

/**
 *  BeanFactoryV1
 *  违背了单一职责原则 SRP Single Re Pricipal
 *  所以需要对进行重构使之满足单一职责原则
 *
 */

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
