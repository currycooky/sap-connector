package com.curry.sap.conn.convert.basic;

import com.curry.sap.conn.ReflectProperties;
import com.sap.conn.jco.JCoRecord;

import java.util.function.BiFunction;

/**
 * 基础类型转换
 *
 * @author zqz
 * @since 2022-12-02
 */
public interface BasicConverter<T> extends BiFunction<JCoRecord, ReflectProperties, T> {
}
