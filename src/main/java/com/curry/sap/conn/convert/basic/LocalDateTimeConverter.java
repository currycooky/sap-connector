package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class LocalDateTimeConverter implements BasicConverter<LocalDateTime> {
    @Override
    public LocalDateTime apply(JCoRecord field, ReflectProperties properties) {
        return LocalDateTime.parse(field.getString(properties.sapFieldName()), DateTimeFormatter.ofPattern(properties.formatter()));
    }
}
