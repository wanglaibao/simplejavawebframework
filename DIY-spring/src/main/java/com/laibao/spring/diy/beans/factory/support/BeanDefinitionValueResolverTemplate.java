package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.factory.config.RuntimeBeanReference;
import com.laibao.spring.diy.beans.factory.config.TypedStringValue;

public abstract class BeanDefinitionValueResolverTemplate {

    public Object resolveValueIfNecessary(Object value) {

        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;

            String refName = ref.getBeanName();

            Object bean = getBeanByName(refName);//factory.getBean(refName);

            return bean;
        }else if (value instanceof TypedStringValue){

            return ((TypedStringValue) value).getValue();

        } else {
            //TODO
            return new RuntimeException("the value "+ value + " has not implemented");
        }

    }

    protected abstract Object getBeanByName(String beanName);
}
