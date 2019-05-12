package com.laibao.spring.diy.test.v1;

import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.io.FileSystemResource;
import com.laibao.spring.diy.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class ResourceV1Test {

    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println(resource.getDescription());
    }


    @Test
    public void testFileSystemResource() throws IOException {
        //下面采用的是绝对路径是一种比较不好的写法，后续可以进行改进
        //Resource resource = new FileSystemResource("D://IdeaProjects//git//simplejavawebframework//DIY-spring//src//test//resources//petstore-v1.xml");

        //将绝对路径修改为相对路径
        Resource resource = new FileSystemResource("src//test//resources//petstore-v1.xml");

        InputStream inputStream = resource.getInputStream();
        Assert.assertNotNull(inputStream);
        if (inputStream != null) {
            inputStream.close();
        }
        System.out.println(resource.getDescription());
    }
}
