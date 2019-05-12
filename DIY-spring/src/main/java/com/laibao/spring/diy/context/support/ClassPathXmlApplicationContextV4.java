package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;

public class ClassPathXmlApplicationContextV4 extends AbstractApplicationContextV2{

    public ClassPathXmlApplicationContextV4(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path,this.getBeanClassLoader());
    }
}
