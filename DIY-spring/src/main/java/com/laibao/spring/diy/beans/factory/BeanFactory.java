package com.laibao.spring.diy.beans.factory;

import com.laibao.spring.diy.beans.BeanDefinition;

public interface BeanFactory {

    BeanDefinition getBeanDefinition(String beanId);


    Object getBean(String beanId);

}
