package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.factory.BeanCreationException;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.BeanFactoryV1;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV1;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryV1Test {

    @Test
    public void testGetBean() {

        BeanFactoryV1 factory = new DefaultBeanFactoryV1("petstore-v1.xml");

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

        assertEquals("com.laibao.spring.diy.service.v1.PetStoreService",beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidXML() {
        try{
           new DefaultBeanFactoryV1("xxx-v1.xml");
        }catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }

    @Test
    public void testInvalidBean(){
        try{
            BeanFactoryV1 factory = new DefaultBeanFactoryV1("petstore-v1.xml");
            factory.getBean("invalidBean");
        }catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }
}
