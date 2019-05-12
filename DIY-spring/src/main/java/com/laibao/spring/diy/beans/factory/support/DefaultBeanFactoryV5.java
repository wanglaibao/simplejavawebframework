package com.laibao.spring.diy.beans.factory.support;

import com.laibao.spring.diy.beans.BeanDefinitionV3;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.config.ConfigurableFactoryV1;
import com.laibao.spring.diy.util.Assert;
import com.laibao.spring.diy.util.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultBeanFactoryV5 extends DefaultSingletonBeanRegistry
                                    implements ConfigurableFactoryV1,BeanDefinitionRegistryV3{

    private final Map<String,BeanDefinitionV3> beanDefinitionMap = new ConcurrentHashMap<>();

    private ClassLoader beanClassLoader;

    public DefaultBeanFactoryV5() {

    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return  (this.beanClassLoader != null) ? beanClassLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public Object getBean(String beanId) {
        Assert.notNull(beanId,"beanId can not be null");
        BeanDefinitionV3 beanDefinition = getBeanDefinitionV3(beanId);
        if (beanDefinition == null) {
            throw new BeanCreationException("Bean Definition dose not exist !");
        }

        //判断BeanDefinition的作用域范围
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registerSingleton(beanId,bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinitionV3 beanDefinition) {

        ClassLoader classLoader = this.getBeanClassLoader();//ClassUtils.getDefaultClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clz = classLoader.loadClass(beanClassName);
            return clz.newInstance();
        }catch (Exception e) {
            throw new BeanCreationException("bean creation for "+beanClassName+ " failed ",e);
        }
    }


    @Override
    public BeanDefinitionV3 getBeanDefinitionV3(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    @Override
    public void registerBeanDefinitionV3(String beanId, BeanDefinitionV3 beanDefinition) {
        this.beanDefinitionMap.put(beanId,beanDefinition);
    }
}
