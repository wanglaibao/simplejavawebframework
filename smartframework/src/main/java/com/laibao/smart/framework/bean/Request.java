package com.laibao.smart.framework.bean;

/**
 * 封装请求信息
 * @author laibao wang
 * @date 2018-09-12
 * @version 1.0
 */
public final class Request {

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求路径
     */
    private String requestPath;

    public Request(String requestMethod,String requestPath) {
        this.requestMethod = requestMethod;
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestPath() {
        return requestPath;
    }
/*
    @Override
    public int hashCode() {
	    return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
	    return EqualsBuilder.reflectionEquals(this,obj);
    }
*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((requestMethod == null) ? 0 : requestMethod.hashCode());
        result = prime * result + ((requestPath == null) ? 0 : requestPath.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Request other = (Request) obj;
        if (requestMethod == null) {
            if (other.requestMethod != null) {
                return false;
            }
        } else if (!requestMethod.equals(other.requestMethod)) {
            return false;
        }

        if (requestPath == null) {
            if (other.requestPath != null) {
                return false;
            }
        } else if (!requestPath.equals(other.requestPath)) {
            return false;
        }
        return true;
    }
}
