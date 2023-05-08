package com.curry.sap.conn;

/**
 * 请求SAP出错时抛出异常
 *
 * @author zqz
 * @since 2022-11-30
 */
public class SAPConnectionException extends RuntimeException {
    public SAPConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
