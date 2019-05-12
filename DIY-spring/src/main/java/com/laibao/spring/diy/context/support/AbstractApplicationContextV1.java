package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV2;
import com.laibao.spring.diy.context.ApplicationContextV1;
import com.laibao.spring.diy.core.io.Resource;

/**
 * AbstractApplicationContextV1抽象类,通过模板模式,抽象出通用的访问逻辑
 * 并且提供一个抽象方法getResourceByPath供各个子类进行实现
 * getResourceByPath方法返回的资源Resource接口抽象了资源的多样性
 * 可以通过统一的方式进行访问
 */
public abstract class AbstractApplicationContextV1 implements ApplicationContextV1 {

    private DefaultBeanFactoryV2 factory;

    protected AbstractApplicationContextV1(String configFile) {
        factory = new DefaultBeanFactoryV2();
        XmlBeanDefinitionReaderV2 reader = new XmlBeanDefinitionReaderV2(factory);
        Resource resource = this.getResourceByPath(configFile);   //new ClassPathResource(configFile);
        reader.loadBeanDefinition(resource);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    protected abstract Resource getResourceByPath(String path);
}
