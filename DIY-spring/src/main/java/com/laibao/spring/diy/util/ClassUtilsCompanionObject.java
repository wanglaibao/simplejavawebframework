package com.laibao.spring.diy.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.laibao.spring.diy.util.ClassUtils.*;

public final class ClassUtilsCompanionObject {

    static {
        primitiveWrapperTypeMap.put(Boolean.class, boolean.class);

        primitiveWrapperTypeMap.put(Byte.class, byte.class);

        primitiveWrapperTypeMap.put(Character.class, char.class);

        primitiveWrapperTypeMap.put(Double.class, double.class);

        primitiveWrapperTypeMap.put(Float.class, float.class);

        primitiveWrapperTypeMap.put(Integer.class, int.class);

        primitiveWrapperTypeMap.put(Long.class, long.class);

        primitiveWrapperTypeMap.put(Short.class, short.class);

        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperTypeMap.entrySet()) {
            primitiveTypeToWrapperMap.put(entry.getValue(), entry.getKey());
            registerCommonClasses(entry.getKey());
        }

        Set<Class<?>> primitiveTypes = new HashSet<Class<?>>(32);

        primitiveTypes.addAll(primitiveWrapperTypeMap.values());

        primitiveTypes.addAll(Arrays.asList(new Class<?>[] {boolean[].class, byte[].class, char[].class, double[].class,
                                                            float[].class, int[].class, long[].class, short[].class}));

        primitiveTypes.add(void.class);

        for (Class<?> primitiveType : primitiveTypes) {
            primitiveTypeNameMap.put(primitiveType.getName(), primitiveType);
        }

        registerCommonClasses(Boolean[].class, Byte[].class, Character[].class, Double[].class,
                                Float[].class, Integer[].class, Long[].class, Short[].class);

        registerCommonClasses(Number.class, Number[].class, String.class, String[].class,
                                Object.class, Object[].class, Class.class, Class[].class);

        registerCommonClasses(Throwable.class, Exception.class, RuntimeException.class,
                                Error.class, StackTraceElement.class, StackTraceElement[].class);
    }

}
