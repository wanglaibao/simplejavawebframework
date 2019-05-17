package com.laibao.spring.diy.test.v4;

import com.laibao.spring.diy.beans.BeanDefinitionV5;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV8;
import com.laibao.spring.diy.context.annotation.ClassPathBeanDefinitionScanner;
import com.laibao.spring.diy.context.annotation.ScannedGenericBeanDefinition;
import com.laibao.spring.diy.core.annotation.AnnotationAttributes;
import com.laibao.spring.diy.core.type.AnnotationMetadata;
import com.laibao.spring.diy.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ClassPathBeanDefinitionScannerTest {

    @Test
    public void testParseScanedBeanDefinition() {
        DefaultBeanFactoryV8 factory = new DefaultBeanFactoryV8();
        String basePackages = "com.laibao.spring.diy.service.v4,com.laibao.spring.diy.dao.v4";

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(factory);
        scanner.doScan(basePackages);

        String annotation = Component.class.getName();

        {
            BeanDefinitionV5 beanDefinition = factory.getBeanDefinitionV5("petStore");
            Assert.assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition scannedGenericBeanDefinition = (ScannedGenericBeanDefinition) beanDefinition;
            AnnotationMetadata annotationMetadata = scannedGenericBeanDefinition.getMetadata();

            assertTrue(annotationMetadata.hasAnnotation(annotation));
            AnnotationAttributes attributes = annotationMetadata.getAnnotationAttributes(annotation);
            Assert.assertEquals("petStore", attributes.get("value"));

        }

        {
            BeanDefinitionV5 beanDefinition = factory.getBeanDefinitionV5("accountDao");
            assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition scannedGenericBeanDefinition = (ScannedGenericBeanDefinition)beanDefinition;
            AnnotationMetadata annotationMetadata = scannedGenericBeanDefinition.getMetadata();
            assertTrue(annotationMetadata.hasAnnotation(annotation));
        }
        {
            BeanDefinitionV5 beanDefinition = factory.getBeanDefinitionV5("itemDao");
            assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition scannedGenericBeanDefinition = (ScannedGenericBeanDefinition)beanDefinition;
            AnnotationMetadata annotationMetadata = scannedGenericBeanDefinition.getMetadata();
            assertTrue(annotationMetadata.hasAnnotation(annotation));
        }
    }
}
