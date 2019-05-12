package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReader;
import com.laibao.spring.diy.context.ApplicationContextV1;

/**
 * 实现ApplicationContextV1接口
 * 并且通过XmlBeanDefinitionReader来读取和加载XML配置文件
 */
public class FileSystemXmlApplicationContextV1 implements ApplicationContextV1 {

    private DefaultBeanFactoryV2 factory;

    public FileSystemXmlApplicationContextV1(String configFile) {
        factory = new DefaultBeanFactoryV2();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinition(configFile);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
