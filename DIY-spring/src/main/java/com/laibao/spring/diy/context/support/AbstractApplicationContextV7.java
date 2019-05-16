package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV7;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV5;
import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.util.ClassUtils;

public abstract class AbstractApplicationContextV7 implements ApplicationContextV2 {

    private DefaultBeanFactoryV7 factory;

    private ClassLoader beanClassLoader;

    protected AbstractApplicationContextV7(String configFile) {
        this(configFile, ClassUtils.getDefaultClassLoader());
    }

    protected AbstractApplicationContextV7(String configFile,ClassLoader classLoader) {
        factory = new DefaultBeanFactoryV7();
        XmlBeanDefinitionReaderV5 reader = new XmlBeanDefinitionReaderV5(factory);
        Resource resource = this.getResourceByPath(configFile);   //new ClassPathResource(configFile);
        reader.loadBeanDefinition(resource);
        factory.setBeanClassLoader(this.getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = beanClassLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null) ? beanClassLoader : ClassUtils.getDefaultClassLoader();
    }

    protected abstract Resource getResourceByPath(String path);
}
