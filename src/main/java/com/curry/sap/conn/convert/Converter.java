package com.curry.sap.conn.convert;

/**
 * 转换数据
 *
 * @author zqz
 * @since 2022-12-01
 */
public interface Converter<T> {
    /**
     * 转换数据
     *
     * @param obj 原始数据
     * @return 转换后的目标类型数据
     */
    T convert(Object obj);
}
