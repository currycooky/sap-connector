package com.curry.sap.conn;

import com.curry.sap.conn.convert.Converter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 映射SAP返回的字段名
 *
 * @author zqz
 * @since 2022-11-30
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SapField {
    /**
     * SAP返回的字段名
     */
    String value();

    /**
     * 设置值方法名
     */
    String setter() default "";

    /**
     * 获取值方法名
     */
    String getter() default "";

    /**
     * 设置日期时间转换的格式
     */
    String formatter() default "";

    /**
     * 数据转换方法
     */
    Class<? extends Converter> converter() default Converter.class;
}
