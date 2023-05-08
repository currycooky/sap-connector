package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

/**
 * Long类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class LongConverter implements BasicConverter<Long> {
    @Override
    public Long apply(JCoRecord field, ReflectProperties properties) {
        return field.getLong(properties.sapFieldName());
    }
}
