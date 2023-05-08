package com.curry.sap.conn;

/**
 * 设置值时出错
 *
 * @author zqz
 * @since 2022-12-06
 */
public class SetValueException extends RuntimeException {
    public SetValueException(String message, Throwable cause) {
        super(message, cause);
    }
}
