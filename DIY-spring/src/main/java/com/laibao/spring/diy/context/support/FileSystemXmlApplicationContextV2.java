package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV2;
import com.laibao.spring.diy.context.ApplicationContextV1;
import com.laibao.spring.diy.core.io.FileSystemResource;
import com.laibao.spring.diy.core.io.Resource;

public class FileSystemXmlApplicationContextV2 implements ApplicationContextV1{

    private DefaultBeanFactoryV2 factory;

    public FileSystemXmlApplicationContextV2(String configFile) {
        factory = new DefaultBeanFactoryV2();
        XmlBeanDefinitionReaderV2 reader = new XmlBeanDefinitionReaderV2(factory);
        Resource resource = new FileSystemResource(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
