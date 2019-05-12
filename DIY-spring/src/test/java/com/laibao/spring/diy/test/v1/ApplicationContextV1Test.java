package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.context.ApplicationContextV1;
import com.laibao.spring.diy.context.support.ClassPathXmlApplicationContextV1;
import com.laibao.spring.diy.context.support.FileSystemXmlApplicationContextV1;
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

    @Test
    public void testGetBeanFromFileSystem() {

        // D://IdeaProjects//git//simplejavawebframework//DIY-spring//

        //ApplicationContextV1 applicationContext = new FileSystemXmlApplicationContextV1("D://IdeaProjects//git//simplejavawebframework//DIY-spring//src//test//resources//petstore-v1.xml");

        ApplicationContextV1 applicationContext = new FileSystemXmlApplicationContextV1("src//test//resources//petstore-v1.xml");

        PetStoreService petStoreService = (PetStoreService) applicationContext.getBean("petStore");

        assertNotNull(petStoreService);
    }
}
