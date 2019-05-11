package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReader;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class BeanFactoryV2Test {

    private DefaultBeanFactoryV2 factory;

    private XmlBeanDefinitionReader reader;

    @Before
    public void setUp() {
         factory = new DefaultBeanFactoryV2();

         reader = new XmlBeanDefinitionReader(factory);
    }

    @After
    public void tearDown() {
        System.out.println("进行必要的清理工作");
    }

    @Test
    public void testGetBean() {

        reader.loadBeanDefinition("petstore-v1.xml");

        assertEquals("com.laibao.spring.diy.service.v1.PetStoreService",factory.getBeanDefinition("petStore").getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStoreService);
    }


    @Test
    public void testInvalidXML() {
        try{
            reader.loadBeanDefinition("xxx-v1.xml");

        }catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }

    @Test
    public void testInvalidBean(){
        try{
            reader.loadBeanDefinition("petstore-v1.xml");
            factory.getBean("invalidBean");
        }catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }
}
