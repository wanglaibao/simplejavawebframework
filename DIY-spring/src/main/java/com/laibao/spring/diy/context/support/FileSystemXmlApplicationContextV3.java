package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.core.io.FileSystemResource;
import com.laibao.spring.diy.core.io.Resource;

public class FileSystemXmlApplicationContextV3 extends AbstractApplicationContext{


    public FileSystemXmlApplicationContextV3(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
