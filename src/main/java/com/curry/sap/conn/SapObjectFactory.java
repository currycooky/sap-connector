package com.curry.sap.conn;

import com.curry.sap.conn.convert.ConvertException;
import com.curry.sap.conn.convert.basic.*;
import com.sap.conn.jco.JCoRecord;

import java.beans.Beans;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * 从SAP结果构建出对象
 *
 * @author zqz
 * @since 2023-04-12
 */
public class SapObjectFactory {

    SapObjectFactory() {
    }

    public <T> T create(Class<T> clazz, JCoRecord field) {
        T obj = newInstanceByClass(clazz);
        Map<String, ReflectProperties> methodMap = SingleSapReflect.INSTANCE.sapReflect().mapping(clazz);
        // 遍历类配置的字段，给这些字段设置值
        methodMap.forEach((k, v) -> invokeSetterMethod(field, obj, v));
        return obj;
    }

    private <T> void invokeSetterMethod(JCoRecord table, T t, ReflectProperties properties) {
        Method setter = properties.setter();
        // 优先使用自定义转换器
        if (properties.hasCustomConverter()) {
            invoke(t, properties.converter().convert(table.getValue(properties.sapFieldName())), setter);
            return;
        }
        Class<?> parameterType = setter.getParameterTypes()[0];
        if (BASIC_TYPE_CONVERTERS.containsKey(parameterType)) {
            invoke(t, BASIC_TYPE_CONVERTERS.get(parameterType).apply(table, properties), setter);
        } else {
            throw new ConvertException("未适配赋值的类型：" + parameterType.getName() + "。请使用com.lesso.curry.SapFieldName.converter自定义赋值");
        }
    }

    private void invoke(Object obj, Object arg, Method setter) {
        try {
            setter.invoke(obj, arg);
        } catch (Exception e) {
            throw new SetValueException("调用" + setter.getDeclaringClass().getName() + "的" + setter.getName() + "方法出错", e);
        }
    }

    private <T> T newInstanceByClass(Class<T> clazz) {
        try {
            return clazz.cast(Beans.instantiate(this.getClass().getClassLoader(), clazz.getName()));
        } catch (Exception e) {
            throw new SAPConnectionException("创建对象" + clazz.getSimpleName() + "出错，请检查是否有合法构造方法", e);
        }
    }

    private static final Map<Class<?>, BiFunction<JCoRecord, ReflectProperties, ?>> BASIC_TYPE_CONVERTERS;

    static {
        BASIC_TYPE_CONVERTERS = new HashMap<>();
        BASIC_TYPE_CONVERTERS.put(String.class, new StringConverter());
        BASIC_TYPE_CONVERTERS.put(Integer.class, new IntConverter());
        BASIC_TYPE_CONVERTERS.put(int.class, new IntConverter());
        BASIC_TYPE_CONVERTERS.put(boolean.class, new BooleanConverter());
        BASIC_TYPE_CONVERTERS.put(Boolean.class, new BooleanConverter());
        BASIC_TYPE_CONVERTERS.put(Long.class, new LongConverter());
        BASIC_TYPE_CONVERTERS.put(long.class, new LongConverter());
        BASIC_TYPE_CONVERTERS.put(Double.class, new DoubleConverter());
        BASIC_TYPE_CONVERTERS.put(double.class, new DoubleConverter());
        BASIC_TYPE_CONVERTERS.put(BigInteger.class, new BigIntegerConverter());
        BASIC_TYPE_CONVERTERS.put(BigDecimal.class, new BigDecimalConverter());
        BASIC_TYPE_CONVERTERS.put(Date.class, new DateConverter());
        BASIC_TYPE_CONVERTERS.put(LocalDate.class, new LocalDateConverter());
        BASIC_TYPE_CONVERTERS.put(LocalTime.class, new LocalTimeConverter());
        BASIC_TYPE_CONVERTERS.put(LocalDateTime.class, new LocalDateTimeConverter());
    }
}
