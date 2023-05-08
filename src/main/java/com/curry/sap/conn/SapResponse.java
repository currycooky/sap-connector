package com.curry.sap.conn;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * SAP响应处理
 *
 * @author zqz
 * @since 2023-04-12
 */
public class SapResponse {
    private final JCoFunction function;

    SapResponse(JCoFunction function) {
        this.function = function;
    }

    public <T> List<T> toList(String key, Class<T> clazz) {
        JCoTable table = this.function.getTableParameterList().getTable(key);
        if (table == null) {
            throw new IllegalArgumentException("未获取到返回数据。表名：" + key + "，接口：" + function.getName());
        }
        int rows = table.getNumRows();
        SapObjectFactory factory = new SapObjectFactory();
        return IntStream.range(0, rows).mapToObj(i -> {
            table.setRow(i);
            return factory.create(clazz, table);
        }).collect(Collectors.toList());
    }

    public String output(String key) {
        return this.function.getExportParameterList().getString(key);
    }

    public <T> T output(Class<T> clazz) {
        return new SapObjectFactory().create(clazz, this.function.getExportParameterList());
    }

    public String toXML() {
        return this.function.toXML();
    }
}
