package com.curry.sap.conn;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 映射实体类
 *
 * @author zqz
 * @since 2022-12-01
 */
public class SapReflect {
    private final ConcurrentHashMap<Class<?>, Map<String, ReflectProperties>> cache = new ConcurrentHashMap<>();

    public Map<String, ReflectProperties> mapping(Class<?> clazz) {
        Map<String, ReflectProperties> map = cache.get(clazz);
        if (map != null) {
            return map;
        }
        map = new HashMap<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            SapField sapField = field.getAnnotation(SapField.class);
            if (sapField == null) {
                continue;
            }
            map.put(field.getName(), new ReflectProperties(setter(clazz, field, sapField), getter(clazz, field, sapField), sapField));
        }
        cache.put(clazz, map);
        return map;
    }

    private Method setter(Class<?> clazz, Field field, SapField sapField) {
        String setterName = setterName(field.getName(), sapField.setter());
        return getMethod(clazz, field, setterName);
    }

    private Method getter(Class<?> clazz, Field field, SapField sapField) {
        String getterName = getterName(field.getName(), sapField.getter());
        return getMethod(clazz, null, getterName);
    }

    private String getterName(String fieldName, String getter) {
        return methodName(fieldName, getter, "get");
    }


    private String setterName(String fieldName, String setter) {
        return methodName(fieldName, setter, "set");
    }

    private Method getMethod(Class<?> clazz, Field field, String methodName) {
        try {
            if (field == null) {
                return clazz.getMethod(methodName);
            }
            return clazz.getMethod(methodName, field.getType());
        } catch (Exception e) {
            throw new SAPConnectionException("未从" + clazz.getName() + "中查询到" + methodName + "方法", e);
        }
    }

    private String methodName(String fieldName, String methodName, String prefix) {
        if (methodName != null && !Objects.equals(methodName.trim(), "")) {
            return methodName;
        }
        char[] chars = fieldName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return prefix + new String(chars);
    }

}
