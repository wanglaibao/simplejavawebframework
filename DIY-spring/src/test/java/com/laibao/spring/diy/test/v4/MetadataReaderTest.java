package com.laibao.spring.diy.test.v4;

import com.laibao.spring.diy.core.annotation.AnnotationAttributes;
import com.laibao.spring.diy.core.io.ClassPathResource;
import com.laibao.spring.diy.core.type.AnnotationMetadata;
import com.laibao.spring.diy.core.type.classreading.MetadataReader;
import com.laibao.spring.diy.core.type.classreading.SimpleMetadataReader;
import com.laibao.spring.diy.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class MetadataReaderTest {

    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/laibao/spring/diy/service/v4/PetStoreService.class");
        MetadataReader reader = new SimpleMetadataReader(resource);

        AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(annotationMetadata.hasAnnotation(annotation));
        AnnotationAttributes attributes = annotationMetadata.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        Assert.assertFalse(annotationMetadata.isAbstract());
        Assert.assertFalse(annotationMetadata.isFinal());
        Assert.assertEquals("com.laibao.spring.diy.service.v4.PetStoreService", annotationMetadata.getClassName());
    }
}
