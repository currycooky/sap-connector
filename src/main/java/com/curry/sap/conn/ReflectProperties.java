package com.curry.sap.conn;

import com.curry.sap.conn.convert.ConvertException;
import com.curry.sap.conn.convert.Converter;

import java.lang.reflect.Method;

/**
 * 类反射出来的数据
 *
 * @author zqz
 * @since 2023-04-12
 */
public class ReflectProperties {
    private final Method setter;

    private final Method getter;

    private final SapField sapField;

    public ReflectProperties(Method setter, Method getter, SapField sapField) {
        this.setter = setter;
        this.getter = getter;
        this.sapField = sapField;
    }

    public Method setter() {
        return setter;
    }

    public Method getter() {
        return getter;
    }

    public String sapFieldName() {
        return sapField.value();
    }

    public String formatter() {
        return sapField.formatter();
    }

    public boolean hasCustomConverter() {
        return sapField.converter() != Converter.class;
    }

    @SuppressWarnings("all")
    public Converter<?> converter() {
        try {
            return sapField.converter().newInstance();
        } catch (Exception e) {
            throw new ConvertException("无法创建自定义转换类：" + sapField.converter().getName(), e);
        }
    }
}
