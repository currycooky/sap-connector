package com.curry.sap.conn.mock;

import com.sap.conn.jco.JCoListMetaData;
import com.sap.conn.jco.JCoParameterFieldIterator;
import com.sap.conn.jco.JCoParameterList;

import java.util.Map;

/**
 * 输出字段
 *
 * @author zqz
 * @since 2023-04-13
 */
public class MockExportParameter extends AbstractJCoRecordIgnore implements JCoParameterList {
    private final Map<String, Object> map;

    public MockExportParameter(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public Object getValue(String s) {
        return map.get(s);
    }

    @Override
    public String getString(String s) {
        return String.valueOf(map.get(s));
    }

    @Override
    public JCoListMetaData getListMetaData() {
        return null;
    }

    @Override
    public JCoParameterFieldIterator getParameterFieldIterator() {
        return null;
    }

    @Override
    public boolean isActive(int i) {
        return false;
    }

    @Override
    public boolean isActive(String s) {
        return false;
    }

    @Override
    public void setActive(int i, boolean b) {

    }

    @Override
    public void setActive(String s, boolean b) {

    }
}
