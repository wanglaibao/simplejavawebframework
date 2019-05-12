package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.beans.BeanDefinitionV2;
import com.laibao.spring.diy.beans.BeanException;
import com.laibao.spring.diy.beans.factory.BeanDefinitionStoreException;
import com.laibao.spring.diy.beans.factory.support.DefaultBeanFactoryV4;
import com.laibao.spring.diy.beans.factory.xml.XmlBeanDefinitionReaderV3;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BeanFactoryV3Test {

    private DefaultBeanFactoryV4 factory;

    private XmlBeanDefinitionReaderV3 reader;

    Resource resource = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactoryV4();

        reader = new XmlBeanDefinitionReaderV3(factory);

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

        assertEquals("com.laibao.spring.diy.service.v1.PetStoreService",factory.getBeanDefinitionV2("petStore").getBeanClassName());

        BeanDefinitionV2 beanDefinition = factory.getBeanDefinitionV2("petStore");

        assertTrue(beanDefinition.isSingleton());

        assertFalse(beanDefinition.isPrototype());


        assertEquals(BeanDefinitionV2.SCOPE_DEFAULT, beanDefinition.getScope());

        PetStoreService petStoreService = null;
        petStoreService = (PetStoreService) factory.getBean("petStore");

        PetStoreService petStoreService1 = (PetStoreService) factory.getBean("petStore");
        assertTrue(petStoreService.equals(petStoreService1));

        assertNotNull(petStoreService);
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
