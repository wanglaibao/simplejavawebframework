package com.laibao.spring.diy.test.v4;

import com.laibao.spring.diy.beans.BeanDefinitionV5;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV8;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV6;
import com.laibao.spring.diy.context.annotation.ScannedGenericBeanDefinition;
import com.laibao.spring.diy.core.annotation.AnnotationAttributes;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.core.type.AnnotationMetadata;
import com.laibao.spring.diy.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

public class XmlBeanDefinitionReaderTest {

    @Test
    public void  testParseScanedBean() {
        DefaultBeanFactoryV8 factory = new DefaultBeanFactoryV8();
        XmlBeanDefinitionReaderV6 reader = new XmlBeanDefinitionReaderV6(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinition(resource);
        String annotation = Component.class.getName();

        {
            BeanDefinitionV5 beanDefinition = factory.getBeanDefinitionV5("petStore");
            Assert.assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)beanDefinition;
            AnnotationMetadata annotationMetadata = sbd.getMetadata();


            Assert.assertTrue(annotationMetadata.hasAnnotation(annotation));
            AnnotationAttributes attributes = annotationMetadata.getAnnotationAttributes(annotation);
            Assert.assertEquals("petStore", attributes.get("value"));
        }

        {
            BeanDefinitionV5 beanDefinition = factory.getBeanDefinitionV5("accountDao");
            Assert.assertTrue(beanDefinition instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)beanDefinition;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }

        {
            BeanDefinitionV5 bd = factory.getBeanDefinitionV5("itemDao");
            Assert.assertTrue(bd instanceof ScannedGenericBeanDefinition);
            ScannedGenericBeanDefinition sbd = (ScannedGenericBeanDefinition)bd;
            AnnotationMetadata amd = sbd.getMetadata();
            Assert.assertTrue(amd.hasAnnotation(annotation));
        }
    }
}
