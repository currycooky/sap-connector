package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

/**
 * Long类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class IntConverter implements BasicConverter<Integer> {
    @Override
    public Integer apply(JCoRecord field, ReflectProperties properties) {
        return field.getInt(properties.sapFieldName());
    }
}
