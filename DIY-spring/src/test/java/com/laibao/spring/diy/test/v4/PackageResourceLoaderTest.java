package com.laibao.spring.diy.test.v4;

import com.laibao.spring.diy.core.io.Resource;
import com.laibao.spring.diy.core.io.support.PackageResourceLoader;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class PackageResourceLoaderTest {

    @Test
    public void  testGetResourcesFromPackagePath() throws IOException {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.laibao.spring.diy.dao.v4");
        assertEquals(2,resources.length);
    }
}
