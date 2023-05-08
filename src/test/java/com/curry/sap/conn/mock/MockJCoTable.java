package com.curry.sap.conn.mock;

import java.util.List;
import java.util.Map;

/**
 * 手动模拟出JCoTable
 *
 * @author zqz
 * @since 2023-04-13
 */
public class MockJCoTable extends AbstractJCoTableIgnore {
    private final List<Map<String, Object>> data;
    private int index = 0;

    public MockJCoTable(List<Map<String, Object>> data) {
        this.data = data;
    }

    @Override
    public void setRow(int i) {
        this.index = i;
    }

    @Override
    public int getNumRows() {
        return this.data.size();
    }

    @Override
    public Object getValue(String s) {
        return this.data.get(index).get(s);
    }

    @Override
    public String getString(String s) {
        return String.valueOf(this.data.get(index).get(s));
    }
}
