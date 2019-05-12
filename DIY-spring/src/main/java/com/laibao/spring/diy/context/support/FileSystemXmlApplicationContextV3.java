package com.laibao.spring.diy.context.support;

import com.laibao.spring.diy.core.io.FileSystemResource;
import com.laibao.spring.diy.core.io.Resource;

/**
 * 继承AbstractApplicationContextV1类实现 ==> 实现了ApplicationContextV1接口
 * 通过XmlBeanDefinitionReaderV2来读取和加载XML配置文件,
 * 并且通过Resource接口统一了资源的访问路径,屏蔽了资源路径的多样性
 *
 */
public class FileSystemXmlApplicationContextV3 extends AbstractApplicationContextV1 {


    public FileSystemXmlApplicationContextV3(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
