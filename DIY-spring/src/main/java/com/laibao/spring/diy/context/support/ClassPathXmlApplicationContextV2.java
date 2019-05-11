package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV2;
import com.laibao.spring.diy.context.ApplicationContextV1;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;

public class ClassPathXmlApplicationContextV2 implements ApplicationContextV1 {


    private DefaultBeanFactoryV2 factory;

    public ClassPathXmlApplicationContextV2(String configFile) {
        factory = new DefaultBeanFactoryV2();
        XmlBeanDefinitionReaderV2 reader = new XmlBeanDefinitionReaderV2(factory);
        Resource resource = new ClassPathResource(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
