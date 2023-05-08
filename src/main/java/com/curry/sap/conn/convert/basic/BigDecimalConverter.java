package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

import java.math.BigDecimal;

/**
 * BigDecimal类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class BigDecimalConverter implements BasicConverter<BigDecimal> {
    @Override
    public BigDecimal apply(JCoRecord field, ReflectProperties properties) {
        return field.getBigDecimal(properties.sapFieldName());
    }
}
