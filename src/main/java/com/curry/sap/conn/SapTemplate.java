package com.curry.sap.conn;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;

/**
 * SAP请求工具
 *
 * @author zqz
 * @since 2022-11-30
 */
public class SapTemplate {
    private final JCoDestination destination;

    private SapTemplate(String name, InitiateDestination initiateDestination) {
        try {
            this.destination = initiateDestination.create(name);
        } catch (Exception e) {
            throw new SAPConnectionException("连接SAP出错：" + name, e);
        }
    }

    public static SapTemplate connect(String name, InitiateDestination getDestination) {
        return new SapTemplate(name, getDestination);
    }

    public static SapTemplate connect(String name) {
        return connect(name, JCoDestinationManager::getDestination);
    }

    public static SapTemplate connect() {
        return connect("default", JCoDestinationManager::getDestination);
    }

    public static SapTemplate connect(InitiateDestination getDestination) {
        return connect("default", getDestination);
    }

    public SapFunctionBuilder function(String functionName) {
        return new SapFunctionBuilder(functionName, this.destination);
    }
}
