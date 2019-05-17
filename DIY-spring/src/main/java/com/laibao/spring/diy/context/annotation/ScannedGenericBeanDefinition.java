package com.laibao.spring.diy.context.annotation;

import com.laibao.spring.diy.beans.factory.annotation.AnnotatedBeanDefinition;
import com.laibao.spring.diy.beans.factory.support.GenericBeanDefinitionV5;
import com.laibao.spring.diy.core.type.AnnotationMetadata;

public class ScannedGenericBeanDefinition extends GenericBeanDefinitionV5 implements AnnotatedBeanDefinition {

    private final AnnotationMetadata metadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();
        this.metadata = metadata;
        setBeanClassName(this.metadata.getClassName());
    }

    @Override
    public AnnotationMetadata getMetadata() {
        return this.metadata;
    }
}
