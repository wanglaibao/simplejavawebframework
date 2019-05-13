package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV6;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV4;
import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.util.ClassUtils;

public abstract class AbstractApplicationContextV6 implements ApplicationContextV2 {

    private DefaultBeanFactoryV6 factory;

    private ClassLoader beanClassLoader;

    protected AbstractApplicationContextV6(String configFile) {
        this(configFile, ClassUtils.getDefaultClassLoader());
    }

    protected AbstractApplicationContextV6(String configFile,ClassLoader classLoader) {
        factory = new DefaultBeanFactoryV6();
        XmlBeanDefinitionReaderV4 reader = new XmlBeanDefinitionReaderV4(factory);
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
