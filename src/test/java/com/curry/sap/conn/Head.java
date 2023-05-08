package com.curry.sap.conn;

import java.util.StringJoiner;

/**
 * SAP接口字段映射类
 *
 * @author zqz
 * @since 2023-04-13
 */
public class Head {
    @SapField("EBELN")
    private String number;

    @SapField("IHREZ")
    private String text;

    @SapField("LIFNR")
    private String code;

    @SapField("NAME1")
    private String name;

    @SapField(value = "LGORT", converter = CustomConverter.class)
    private String warehouse;

    public String getNumber() {
        return number;
    }

    @SuppressWarnings("unused")
    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    @SuppressWarnings("unused")
    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    @SuppressWarnings("unused")
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    @SuppressWarnings("unused")
    public void setName(String name) {
        this.name = name;
    }

    public String getWarehouse() {
        return warehouse;
    }

    @SuppressWarnings("unused")
    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Head.class.getSimpleName() + "[", "]")
            .add("number='" + number + "'")
            .add("text='" + text + "'")
            .add("code='" + code + "'")
            .add("name='" + name + "'")
            .add("warehouse='" + warehouse + "'")
            .toString();
    }
}
