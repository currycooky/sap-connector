package com.curry.sap.conn;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;

/**
 * 获取SAP连接方法
 *
 * @author zqz
 * @since 2023-04-12
 */
@FunctionalInterface
public interface InitiateDestination {
    /**
     * 获取SAP连接的方式
     */
    JCoDestination create(String name) throws JCoException;
}
