package com.laibao.spring.diy.test.v4;

import com.laibao.spring.diy.test.v4.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PackageResourceLoaderTest.class,
        MetadataReaderTest.class,
        ClassReaderTest.class,
        ClassPathBeanDefinitionScannerTest.class,
        XmlBeanDefinitionReaderTest.class
})
public class V4AllTest {
}
