package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.BeanFactoryV1;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV1;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV2;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReader;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class BeanFactoryV2Test {


    @Test
    public void testGetBean() {

        DefaultBeanFactoryV2 factory = new DefaultBeanFactoryV2();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinition("petstore-v1.xml");

        assertEquals("com.laibao.spring.diy.service.v1.PetStoreService",factory.getBeanDefinition("petStore").getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStoreService);
    }


    @Test
    public void testInvalidXML() {
        try{
            DefaultBeanFactoryV2 factory =  new DefaultBeanFactoryV2();
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
            reader.loadBeanDefinition("xxx-v1.xml");

        }catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }

    @Test
    public void testInvalidBean(){
        try{
            DefaultBeanFactoryV2 factory =  new DefaultBeanFactoryV2();
            XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
            reader.loadBeanDefinition("petstore-v1.xml");
            factory.getBean("invalidBean");
        }catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }
}
