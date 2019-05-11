package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.context.ApplicationContextV1;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContextV1;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;


public class ApplicationContextV1Test {

    @Test
    public void testGetBean() {

        ApplicationContextV1 applicationContext = new ClassPathXmlApplicationContextV1("petstore-v1.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);
    }
}
