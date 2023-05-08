package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.curry.sap.conn.convert.ConvertException;
import com.sap.conn.jco.JCoRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date数据转换
 *
 * @author zqz
 * @since 2022-12-02
 */
public class DateConverter implements BasicConverter<Date> {
    @Override
    public Date apply(JCoRecord field, ReflectProperties properties) {
        try {
            return new SimpleDateFormat(properties.formatter()).parse(field.getString(properties.sapFieldName()));
        } catch (Exception e) {
            throw new ConvertException("转换Date数据出错。数据：" +
                    field.getString(properties.sapFieldName()) + "，格式：" + properties.formatter());
        }
    }
}
