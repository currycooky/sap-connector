package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

/**
 * boolean 类型转换
 * <p>
 * 非0即为真，如果是空或者空白默认为false，因为getInt()会返回0
 *
 * @author zqz
 * @since 2022-12-02
 */
public class BooleanConverter implements BasicConverter<Boolean> {
    @Override
    public Boolean apply(JCoRecord field, ReflectProperties properties) {
        return field.getInt(properties.sapFieldName()) > 0;
    }
}
