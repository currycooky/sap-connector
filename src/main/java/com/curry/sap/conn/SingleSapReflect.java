package com.curry.sap.conn;

/**
 * 单例反射类，缓存已经反射过的类
 *
 * @author zqz
 * @since 2022-12-01
 */
public enum SingleSapReflect {
    INSTANCE;

    private final SapReflect sapReflect;

    SingleSapReflect() {
        this.sapReflect = new SapReflect();
    }

    public SapReflect sapReflect() {
        return sapReflect;
    }
}
