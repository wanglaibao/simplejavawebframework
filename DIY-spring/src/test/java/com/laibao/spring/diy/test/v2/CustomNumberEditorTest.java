package com.laibao.spring.diy.test.v2;

import com.laibao.spring.diy.beans.propertyeditors.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CustomNumberEditorTest {

    @Test
    public void testConvertStringToNumber() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class,true);
        editor.setAsText("3");
        Object value = editor.getValue();
        assertTrue(value instanceof Integer);
        assertEquals(3, ((Integer)editor.getValue()).intValue());
        editor.setAsText("");
        assertTrue(editor.getValue() == null);
        try{
            editor.setAsText("3.1");

        }catch(IllegalArgumentException e){
            return ;
        }
        Assert.fail();
    }
}
