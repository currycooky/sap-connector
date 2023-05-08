package com.curry.sap.conn.mock;

import com.sap.conn.jco.JCoRecordFieldIterator;
import com.sap.conn.jco.JCoRecordMetaData;
import com.sap.conn.jco.JCoTable;

/**
 * 屏蔽暂时不需要的方法
 *
 * @author zqz
 * @since 2023-04-13
 */
public abstract class AbstractJCoTableIgnore extends AbstractJCoRecordIgnore implements JCoTable {
    @Override
    public JCoRecordMetaData getRecordMetaData() {
        return null;
    }

    @Override
    public void ensureBufferCapacity(int i) {

    }

    @Override
    public void trimToRows() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFirstRow() {
        return false;
    }

    @Override
    public boolean isLastRow() {
        return false;
    }

    @Override
    public int getNumColumns() {
        return 0;
    }

    @Override
    public void deleteAllRows() {

    }

    @Override
    public void firstRow() {

    }

    @Override
    public void lastRow() {

    }

    @Override
    public boolean nextRow() {
        return false;
    }

    @Override
    public boolean previousRow() {
        return false;
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public void appendRows(int i) {

    }

    @Override
    public void insertRow(int i) {

    }

    @Override
    public void deleteRow() {

    }

    @Override
    public void deleteRow(int i) {

    }

    @Override
    public void appendRow() {

    }

    @Override
    public JCoRecordFieldIterator getRecordFieldIterator() {
        return null;
    }
}
