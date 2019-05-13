package com.laibao.spring.diy.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
                    ApplicationContextV1Test.class,
                    BeanDefinitionTest.class,
                    ConstructorResolverTest.class })
public class V3AllTest {
}
