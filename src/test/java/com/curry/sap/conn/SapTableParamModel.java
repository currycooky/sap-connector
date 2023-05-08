package com.curry.sap.conn;

/**
 * 测试SAP表参数
 *
 * @author zqz
 * @since 2023-04-12
 */
public class SapTableParamModel {
    @SapField("NAME")
    private String name;

    public SapTableParamModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
