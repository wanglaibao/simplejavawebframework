package com.laibao.spring.diy.beans.factory.annotation;

import com.laibao.spring.diy.beans.BeanDefinitionV5;
import com.laibao.spring.diy.core.type.AnnotationMetadata;

/**
 * 新增对注解和auto-scan注入的支持
 */
public interface AnnotatedBeanDefinition extends BeanDefinitionV5{
    AnnotationMetadata getMetadata();
}
