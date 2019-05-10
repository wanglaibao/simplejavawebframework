package com.laibao.spring.diy.beans.factory.support;


import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.BeanFactoryV2;
import com.laibao.spring.diy.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactoryV2 implements BeanFactoryV2,BeanDefinitionRegistry{

    private final Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactoryV2() {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }


    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {

        this.beanDefinitionMap.put(beanId,beanDefinition);

    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition definition = getBeanDefinition(beanId);

        if (definition == null) {
            throw new BeanCreationException("Bean Definition dose not exist !");
        }
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        String beanClassName = definition.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e) {
            throw new BeanCreationException("bean creation for "+beanClassName+ " failed ",e);
        }

    }
}
