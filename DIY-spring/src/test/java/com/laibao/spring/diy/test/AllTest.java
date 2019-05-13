package com.laibao.spring.diy.test;

import com.laibao.spring.diy.test.v1.V1AllTest;
import com.laibao.spring.diy.test.v2.V2AllTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTest.class,
        V2AllTest.class
})
public class AllTest {
}