package com.curry.sap.conn.mock;

import com.sap.conn.jco.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;

/**
 * 屏蔽一些不需要的方法
 *
 * @author zqz
 * @since 2023-04-13
 */
@SuppressWarnings("all")
public abstract class AbstractJCoRecordIgnore implements JCoRecord {
    @Override
    public JCoMetaData getMetaData() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int copyFrom(JCoRecord jCoRecord) {
        return 0;
    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public Object getValue(int i) {
        return null;
    }

    @Override
    public String getString(int i) {
        return null;
    }

    @Override
    public char getChar(int i) {
        return 0;
    }

    @Override
    public byte getByte(int i) {
        return 0;
    }

    @Override
    public byte getByte(String s) {
        return 0;
    }

    @Override
    public float getFloat(int i) {
        return 0;
    }

    @Override
    public float getFloat(String s) {
        return 0;
    }

    @Override
    public short getShort(int i) {
        return 0;
    }

    @Override
    public int getInt(int i) {
        return 0;
    }

    @Override
    public long getLong(int i) {
        return 0;
    }

    @Override
    public BigInteger getBigInteger(int i) {
        return null;
    }

    @Override
    public double getDouble(int i) {
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal(int i) {
        return null;
    }

    @Override
    public Date getDate(int i) {
        return null;
    }

    @Override
    public Date getTime(int i) {
        return null;
    }

    @Override
    public byte[] getByteArray(int i) {
        return new byte[0];
    }

    @Override
    public char[] getCharArray(int i) {
        return new char[0];
    }

    @Override
    public InputStream getBinaryStream(int i) {
        return null;
    }

    @Override
    public Reader getCharacterStream(int i) {
        return null;
    }

    @Override
    public JCoTable getTable(int i) {
        return null;
    }

    @Override
    public JCoStructure getStructure(int i) {
        return null;
    }

    @Override
    public String toXML(int i) {
        return null;
    }

    @Override
    public String toXML(String s) {
        return null;
    }

    @Override
    public String toXML() {
        return null;
    }

    @Override
    public Writer write(int i, Writer writer) throws IOException {
        return null;
    }

    @Override
    public Writer write(String s, Writer writer) throws IOException {
        return null;
    }

    @Override
    public void setValue(int i, String s) {

    }

    @Override
    public String getClassNameOfValue(String s) {
        return null;
    }

    @Override
    public char getChar(String s) {
        return 0;
    }

    @Override
    public short getShort(String s) {
        return 0;
    }

    @Override
    public int getInt(String s) {
        return 0;
    }

    @Override
    public long getLong(String s) {
        return 0;
    }

    @Override
    public BigInteger getBigInteger(String s) {
        return null;
    }

    @Override
    public double getDouble(String s) {
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal(String s) {
        return null;
    }

    @Override
    public Date getDate(String s) {
        return null;
    }

    @Override
    public Date getTime(String s) {
        return null;
    }

    @Override
    public byte[] getByteArray(String s) {
        return new byte[0];
    }

    @Override
    public char[] getCharArray(String s) {
        return new char[0];
    }

    @Override
    public InputStream getBinaryStream(String s) {
        return null;
    }

    @Override
    public Reader getCharacterStream(String s) {
        return null;
    }

    @Override
    public JCoTable getTable(String s) {
        return null;
    }

    @Override
    public JCoStructure getStructure(String s) {
        return null;
    }

    @Override
    public void setValue(int i, char c) {

    }

    @Override
    public void setValue(int i, char[] chars) {

    }

    @Override
    public void setValue(int i, char[] chars, int i1, int i2) {

    }

    @Override
    public void setValue(int i, short i1) {

    }

    @Override
    public void setValue(int i, int i1) {

    }

    @Override
    public void setValue(int i, long l) {

    }

    @Override
    public void setValue(int i, double v) {

    }

    @Override
    public void setValue(int i, byte[] bytes) {

    }

    @Override
    public void setValue(int i, JCoStructure jCoStructure) {

    }

    @Override
    public void setValue(int i, JCoTable jCoTable) {

    }

    @Override
    public void setValue(int i, Object o) {

    }

    @Override
    public void setValue(String s, String s1) {

    }

    @Override
    public void setValue(String s, char[] chars) {

    }

    @Override
    public void setValue(String s, char[] chars, int i, int i1) {

    }

    @Override
    public void setValue(String s, char c) {

    }

    @Override
    public void setValue(String s, short i) {

    }

    @Override
    public void setValue(String s, int i) {

    }

    @Override
    public void setValue(String s, long l) {

    }

    @Override
    public void setValue(String s, double v) {

    }

    @Override
    public void setValue(int i, float v) {

    }

    @Override
    public void setValue(String s, float v) {

    }

    @Override
    public void setValue(int i, BigDecimal bigDecimal) {

    }

    @Override
    public void setValue(String s, BigDecimal bigDecimal) {

    }

    @Override
    public void setValue(int i, byte b) {

    }

    @Override
    public void setValue(String s, byte b) {

    }

    @Override
    public void setValue(String s, byte[] bytes) {

    }

    @Override
    public void setValue(String s, JCoStructure jCoStructure) {

    }

    @Override
    public void setValue(String s, JCoTable jCoTable) {

    }

    @Override
    public void setValue(String s, Object o) {

    }

    @Override
    public JCoAbapObject getAbapObject(String s) {
        return null;
    }

    @Override
    public JCoAbapObject getAbapObject(int i) {
        return null;
    }

    @Override
    public void setValue(String s, JCoAbapObject jCoAbapObject) {

    }

    @Override
    public void setValue(int i, JCoAbapObject jCoAbapObject) {

    }

    @Override
    public boolean isInitialized(String s) {
        return false;
    }

    @Override
    public boolean isInitialized(int i) {
        return false;
    }

    @Override
    public Iterator<JCoField> iterator() {
        return null;
    }

    @Override
    public JCoFieldIterator getFieldIterator() {
        return null;
    }

    @Override
    public Object clone() {
        return null;
    }
}
