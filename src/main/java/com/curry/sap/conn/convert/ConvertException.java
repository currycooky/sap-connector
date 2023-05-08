package com.curry.sap.conn.convert;

/**
 * 格式化数据出现错误
 *
 * @author zqz
 * @since 2022-12-02
 */
public class ConvertException extends RuntimeException {
    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(String message) {
        super(message);
    }
}
