package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalTime类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class LocalTimeConverter implements BasicConverter<LocalTime> {
    @Override
    public LocalTime apply(JCoRecord field, ReflectProperties properties) {
        return LocalTime.parse(field.getString(properties.sapFieldName()), DateTimeFormatter.ofPattern(properties.formatter()));
    }
}
