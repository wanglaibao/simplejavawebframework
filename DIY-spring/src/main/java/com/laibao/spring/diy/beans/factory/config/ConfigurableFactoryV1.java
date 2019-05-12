package com.laibao.spring.diy.beans.factory.config;

import com.laibao.spring.diy.beans.factory.BeanFactoryV2;

public interface ConfigurableFactoryV1 extends BeanFactoryV2 {

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
