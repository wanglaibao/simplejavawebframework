package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.context.ApplicationContext;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContext;
import com.laibao.spring.diy.service.v1.PetStoreService;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;


public class ApplicationContextTest {

    @Test
    public void testGetBean() {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);
    }
}
