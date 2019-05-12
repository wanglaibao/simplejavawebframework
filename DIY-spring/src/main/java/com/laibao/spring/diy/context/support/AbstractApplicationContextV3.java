package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV3;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV2;
import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.util.ClassUtils;

public abstract class AbstractApplicationContextV3 implements ApplicationContextV2 {


    private DefaultBeanFactoryV3 factory;

    private ClassLoader beanClassLoader;

    protected AbstractApplicationContextV3(String configFile) {
        this(configFile,ClassUtils.getDefaultClassLoader());
    }

    protected AbstractApplicationContextV3(String configFile,ClassLoader classLoader) {
        factory = new DefaultBeanFactoryV3();
        XmlBeanDefinitionReaderV2 reader = new XmlBeanDefinitionReaderV2(factory);
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
