package com.laibao.spring.diy.beans.factory.support;


public class BeanDefinitionValueResolverV2 extends BeanDefinitionValueResolverTemplate{

    private final DefaultBeanFactoryV6 factory;

    public BeanDefinitionValueResolverV2(DefaultBeanFactoryV6 factory) {
        this.factory = factory;
    }

    /*
    public Object resolveValueIfNecessary(Object value) {

        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference ref = (RuntimeBeanReference) value;

            String refName = ref.getBeanName();

            Object bean = factory.getBean(refName);

            return bean;
        }else if (value instanceof TypedStringValue){

            return ((TypedStringValue) value).getValue();

        } else {
            //
            return new RuntimeException("the value "+ value + " has not implemented");
        }

    }*/

    @Override
    protected Object getBeanByName(String beanName) {
        Object bean = factory.getBean(beanName);
        return bean;
    }
}
