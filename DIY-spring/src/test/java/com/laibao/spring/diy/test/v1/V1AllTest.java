package com.laibao.spring.diy.test.v1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BeanFactoryV1Test.class,
                    BeanFactoryV2Test.class,
                    BeanFactoryV3Test.class,
                    ApplicationContextV1Test.class,
                    ApplicationContextV2Test.class,
                    ApplicationContextV3Test.class,
                    ApplicationContextV4Test.class,
                    ResourceV1Test.class})
public class V1AllTest {
}
