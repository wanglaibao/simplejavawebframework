package com.laibao.smart.framework.bean;

/**
 * 返回数据对象
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public final class Data {

    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
