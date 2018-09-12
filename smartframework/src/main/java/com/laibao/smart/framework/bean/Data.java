package com.laibao.smart.framework.bean;

import java.io.Serializable;

/**
 * 返回数据对象
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public final class Data implements Serializable{
    private static final long serialVersionUID = -1724473677011699807L;
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
