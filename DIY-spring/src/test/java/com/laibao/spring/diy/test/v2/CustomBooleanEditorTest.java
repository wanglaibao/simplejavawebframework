package com.laibao.spring.diy.test.v2;

import com.laibao.spring.diy.beans.propertyeditors.CustomBooleanEditor;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomBooleanEditorTest {

    @Test
    public void testConvertStringToBoolean() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);

        editor.setAsText("true");
        assertEquals(true, ((Boolean)editor.getValue()).booleanValue());
        editor.setAsText("false");
        assertEquals(false, ((Boolean)editor.getValue()).booleanValue());

        editor.setAsText("on");
        assertEquals(true, ((Boolean)editor.getValue()).booleanValue());
        editor.setAsText("off");
        assertEquals(false, ((Boolean)editor.getValue()).booleanValue());


        editor.setAsText("yes");
        assertEquals(true, ((Boolean)editor.getValue()).booleanValue());
        editor.setAsText("no");
        assertEquals(false, ((Boolean)editor.getValue()).booleanValue());


        try{
            editor.setAsText("aabbcc");
        }catch(IllegalArgumentException e){
            return;
        }
        Assert.fail();

    }
}
