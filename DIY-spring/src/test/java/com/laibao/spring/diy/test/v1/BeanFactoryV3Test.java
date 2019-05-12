package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.BeanException;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV2;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BeanFactoryV3Test {

    private DefaultBeanFactoryV2 factory;

    private XmlBeanDefinitionReaderV2 reader;

    Resource resource = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactoryV2();

        reader = new XmlBeanDefinitionReaderV2(factory);

        resource = new ClassPathResource("petstore-v1.xml");

    }

    @After
    public void tearDown() {
        System.out.println("进行必要的清理工作");
    }

    @Test
    public void testGetBean() {

        //reader.loadBeanDefinition("petstore-v1.xml");
        reader.loadBeanDefinition(resource);

        assertEquals("com.laibao.spring.diy.service.v1.PetStoreService",factory.getBeanDefinition("petStore").getBeanClassName());

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

        //assertTrue(beanDefinition.isSingleton());

        //assertFalse(beanDefinition.isPrototype());
    }


    @Test
    public void testInvalidXml() {
        Resource resource = new ClassPathResource("xxx.xml");

        try {
            reader.loadBeanDefinition(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("读取xml出错");

    }


    @Test
    public void testInvalidGetBean() {
        try {
            factory.getBean("invalidBean");
        } catch (BeanException e) {
            return;
        }
        Assert.fail("expect BeanException");
    }


}
