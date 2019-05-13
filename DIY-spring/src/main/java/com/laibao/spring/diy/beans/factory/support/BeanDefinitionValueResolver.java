package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.factory.config.RuntimeBeanReference;
import com.laibao.spring.diy.beans.factory.config.TypedStringValue;

/**
 * 把一个bean的名称【或者全路径名称】 转换成Bean实例的过程也通常称之为Resolve
 *
 * eg: <property name="accountDao" ref="accountDao"/>
 *
 * 我们已经通过属性名称找到了对应的accountDao
 *
 * XXXXResolver就是解决accountDao所对应的对象问题
 *
 */
public class BeanDefinitionValueResolver {

    private final DefaultBeanFactoryV5 factory;

    public BeanDefinitionValueResolver(DefaultBeanFactoryV5 factory) {
        this.factory = factory;
    }

    public Object resolveValueIfNecessary(Object value) {

        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;

            String refName = ref.getBeanName();

            Object bean = factory.getBean(refName);

            return bean;
        }else if (value instanceof TypedStringValue){

            return ((TypedStringValue) value).getValue();

        } else {
            //TODO
            return new RuntimeException("the value "+ value + " has not implemented");
        }

    }
}
