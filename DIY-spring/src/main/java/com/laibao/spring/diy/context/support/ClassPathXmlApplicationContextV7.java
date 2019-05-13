package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;

public class ClassPathXmlApplicationContextV7 extends AbstractApplicationContextV6{

    public ClassPathXmlApplicationContextV7(String configFile) {
        super(configFile);
    }

    public ClassPathXmlApplicationContextV7(String configFile, ClassLoader classLoader) {
        super(configFile, classLoader);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }

}
