package com.laibao.smart.framework.bean;

import java.lang.reflect.Method;

/**
 * 封装Action信息
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public final class Handler {

    /**
     *  处理请求的Controller类
     */
    private Class<?> controllerClass;

    /**
     * 处理请求的Method方法
     */
    private Method actionMethod;

    public Handler(Class<?> controllerClass,Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }
}
