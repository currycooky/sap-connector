package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

import java.math.BigInteger;

/**
 * BigInteger类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class BigIntegerConverter implements BasicConverter<BigInteger> {
    @Override
    public BigInteger apply(JCoRecord field, ReflectProperties properties) {
        return field.getBigInteger(properties.sapFieldName());
    }
}
