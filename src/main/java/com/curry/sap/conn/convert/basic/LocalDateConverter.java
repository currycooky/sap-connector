package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate类型转换器
 *
 * @author zqz
 * @since 2022-12-02
 */
public class LocalDateConverter implements BasicConverter<LocalDate> {
    @Override
    public LocalDate apply(JCoRecord field, ReflectProperties properties) {
        return LocalDate.parse(field.getString(properties.sapFieldName()), DateTimeFormatter.ofPattern(properties.formatter()));
    }
}
