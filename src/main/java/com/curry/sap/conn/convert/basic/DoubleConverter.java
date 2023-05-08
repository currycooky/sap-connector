package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

/**
 * Double类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class DoubleConverter implements BasicConverter<Double> {
    @Override
    public Double apply(JCoRecord field, ReflectProperties properties) {
        return field.getDouble(properties.sapFieldName());
    }
}
