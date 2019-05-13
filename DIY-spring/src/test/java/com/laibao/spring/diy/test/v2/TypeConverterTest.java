package com.laibao.spring.diy.test.v2;

import com.laibao.spring.diy.beans.SimpleTypeConverter;
import com.laibao.spring.diy.beans.TypeConverter;
import org.junit.Test;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

public class TypeConverterTest {

    @Test
    public void testConvertStringToInt() {
        try {
            TypeConverter typeConverter = new SimpleTypeConverter();
            Integer i = typeConverter.convertIfNecessary("3", Integer.class);
            assertEquals(3, i.intValue());
            typeConverter.convertIfNecessary("3.1", Integer.class);
        } catch (Exception e) {
            return;
        }

        fail();
    }

    @Test
    public void testConvertStringToBoolean() {
        TypeConverter converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        assertEquals(true, b.booleanValue());

        try {
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        } catch (Exception e) {
            return;
        }
        fail();

    }

}
