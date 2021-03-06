package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;

public class ClassPathXmlApplicationContextV6 extends AbstractApplicationContextV5{


    public ClassPathXmlApplicationContextV6(String configFile) {
        super(configFile);
    }

    public ClassPathXmlApplicationContextV6(String configFile, ClassLoader classLoader) {
        super(configFile, classLoader);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }
}
