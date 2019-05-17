package com.laibao.spring.diy.beans.factory.annotation;

import java.lang.annotation.*;

/**
 *
 * 注解本质上就是元数据，
 * @Target 标注注解的使用地方
 * @Documented 说注解就是一个元素，可以被文档化
 */

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    boolean required() default true;
}
