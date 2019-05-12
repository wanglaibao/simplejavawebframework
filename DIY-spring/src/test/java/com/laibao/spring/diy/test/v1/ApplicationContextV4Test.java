package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContextV4;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class ApplicationContextV4Test {
    @Test
    public void testGetBean() {

        ApplicationContextV2 applicationContext = new ClassPathXmlApplicationContextV4("petstore-v1.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);
    }
}
