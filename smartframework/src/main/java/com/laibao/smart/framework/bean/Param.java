package com.laibao.smart.framework.bean;
import com.laibao.smart.framework.util.CastUtil;
import java.util.Map;

/**
 * 请求参数对象
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public final class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String,Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public int getInt(String name) {
        return CastUtil.castInt(paramMap.get(name));
    }

    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public double getDouble(String name) {
        return CastUtil.castDouble(paramMap.get(name));
    }

    public String getString(String name) {
        return CastUtil.castString(paramMap.get(name));
    }
}
