package cn.rpc.util;

import java.io.Serializable;

/**
 * @author xurw
 * @description .
 * @date 2021/1/20
 */
public class RpcRequest implements Serializable {


    private static final long serialVersionUID = -6150314051289200754L;

    /**
     * 类名
     */
    private String clazzName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class [] ParameterTypes;

    /**
     * 参数
     */
    private Object [] params;


    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return ParameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        ParameterTypes = parameterTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
