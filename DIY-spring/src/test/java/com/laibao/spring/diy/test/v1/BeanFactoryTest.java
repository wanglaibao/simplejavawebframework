package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.beans.BeanDefinition;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.BeanFactory;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactory;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BeanFactoryTest {

    @Test
    public void testGetBean() {

        BeanFactory factory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");

        assertEquals("com.laibao.spring.diy.service.v1.PetStoreService",beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");

        assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidXML() {
        try{
            new DefaultBeanFactory("xxxxxx-v1.xml");
        }catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }

    @Test
    public void testInvalidBean(){

    }
}
