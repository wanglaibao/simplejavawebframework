package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV2;
import com.laibao.spring.diy.context.ApplicationContextV1;
import com.laibao.spring.diy.core.io.FileSystemResource;
import com.laibao.spring.diy.core.io.Resource;

/**
 * 实现ApplicationContextV1接口
 * 通过XmlBeanDefinitionReaderV2来读取和加载XML配置文件,
 * 并且通过 Resource 接口统一了资源的访问路径,屏蔽了资源路径的多样性
 *
 */
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
