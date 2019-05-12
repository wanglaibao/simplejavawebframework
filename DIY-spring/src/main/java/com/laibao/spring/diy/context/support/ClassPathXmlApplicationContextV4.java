package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;

/**
 * 继承AbstractApplicationContextV2抽象类 ===> 实现了ApplicationContextV2接口,从而获取了设置ClassLoader的功能
 * getResourceByPath方法返回的资源Resource接口抽象了资源的多样性
 * 可以通过统一的方式进行访问
 */
public class ClassPathXmlApplicationContextV4 extends AbstractApplicationContextV2{

    public ClassPathXmlApplicationContextV4(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }
}
