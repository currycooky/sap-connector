package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

/**
 * String类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class StringConverter implements BasicConverter<String> {
    @Override
    public String apply(JCoRecord field, ReflectProperties properties) {
        return field.getString(properties.sapFieldName());
    }
}
