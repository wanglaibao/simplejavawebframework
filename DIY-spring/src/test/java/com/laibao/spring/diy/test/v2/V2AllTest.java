package com.laibao.spring.diy.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                     ApplicationContextV1Test.class,
                     BeanDefinitionTest.class,
                     BeanDefinitionValueResolverTest.class,
                     TypeConverterTest.class,
                     CustomNumberEditorTest.class,
                     CustomBooleanEditorTest.class
                    })
public class V2AllTest {
}
