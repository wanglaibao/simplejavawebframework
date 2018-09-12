package com.laibao.smart.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图对象
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public final class View {

    /**
     * 视图路径
     */
    private String path;

    /**
     * 模型数据
     */
    private Map<String,Object> model;


    public View(String path) {
        this.path = path;
        model = new HashMap<>();
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public View addModel(String key,Object value) {
        model.put(key,value);
        return this;
    }
}
