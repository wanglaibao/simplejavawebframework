package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.factory.config.SingletonBeanRegistry;
import com.laibao.spring.diy.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(32);

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "beanName must not null");
        Object oldObject = this.singletonObjects.get(beanName);
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject + "] under bean name "+ beanName + " :" +
                    " there is already object [" + oldObject + "] with name " + beanName);
        }
        this.singletonObjects.put(beanName, singletonObject);
    }
}
