package com.laibao.spring.diy.test.v3;

import com.laibao.spring.diy.context.ApplicationContextV2;
import com.laibao.spring.diy.context.support.AbstractApplicationContextV7;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContextV8;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.service.v3.PetStoreService;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

public class ApplicationContextV1Test {

    @Test
    public void testGetBean_With_Constructor_Argument() {
        ApplicationContextV2 applicationContext = new AbstractApplicationContextV7("petstore-v3.xml"){
            @Override
            protected Resource getResourceByPath(String path) {
                return new ClassPathResource(path);
            }
        };
        //ApplicationContextV2 applicationContext = new ClassPathXmlApplicationContextV7("petstore-v3.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        assertNotNull(petStoreService.getAccountDao());
        assertNotNull(petStoreService.getItemDao());
        assertEquals(1,petStoreService.getVersion());
    }

    @Test
    public void testGetBean_With_Constructor_Argument_Two() {
        ApplicationContextV2 applicationContext = new ClassPathXmlApplicationContextV8("petstore-v3.xml");
        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");
        assertNotNull(petStoreService.getAccountDao());
        assertNotNull(petStoreService.getItemDao());
        assertEquals(1,petStoreService.getVersion());
    }
}
