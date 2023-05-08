package com.curry.sap.conn;

import com.curry.sap.conn.convert.Converter;

/**
 * 测试自定义转换器
 *
 * @author zqz
 * @since 2023-04-13
 */
public class CustomConverter implements Converter<String> {
    @Override
    public String convert(Object obj) {
        return "自定义转换" + obj.toString();
    }
}
