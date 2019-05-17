package com.laibao.spring.diy.test.v4;

import com.laibao.spring.diy.core.annotation.AnnotationAttributes;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.type.classreading.AnnotationMetadataReadingVisitor;
import com.laibao.spring.diy.core.type.classreading.ClassMetadataReadingVisitor;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.asm.ClassReader;

import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;

public class ClassReaderTest {

    @Test
    public void  testGetClassByteCodeMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/laibao/spring/diy/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());
        ClassMetadataReadingVisitor visitor = new ClassMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        assertFalse(visitor.isAbstract());
        assertFalse(visitor.isInterface());
        assertFalse(visitor.isFinal());
        assertEquals("com.laibao.spring.diy.service.v4.PetStoreService", visitor.getClassName());
        assertEquals("java.lang.Object", visitor.getSuperClassName());
        assertEquals(0, visitor.getInterfaceNames().length);

    }


    @Test
    public void  testGetAnnotationMetaData() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/laibao/spring/diy/service/v4/PetStoreService.class");
        ClassReader reader = new ClassReader(resource.getInputStream());
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        reader.accept(visitor, ClassReader.SKIP_DEBUG);
        String annotation = "com.laibao.spring.diy.stereotype.Component";
        Assert.assertTrue(visitor.hasAnnotation(annotation));
        AnnotationAttributes attributes = visitor.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));
    }

}
