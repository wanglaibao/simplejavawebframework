package com.laibao.spring.diy.beans.factory;

import com.laibao.spring.diy.beans.BeanDefinition;

public interface BeanFactoryV1 {

    /**
     *
     * @param beanId
     * @return BeanDefinition
     * 这个方法应该被删除以便实现类只做一件事情[SRP],请参考BeanFactoryV2;
     * 我们可以将该方法抽取到XmlBeanDefinitionReader类中，
     * XmlBeanDefinitionReader类只做 .xml 配置文件的加载和读取,以便形成BeanDefinition定义
     *
     */
    @Deprecated
    BeanDefinition getBeanDefinition(String beanId);


    /**
     *
     * @param beanId
     * @return Object
     */
    Object getBean(String beanId);

}
