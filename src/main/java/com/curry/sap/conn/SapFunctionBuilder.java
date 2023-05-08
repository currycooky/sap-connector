package com.curry.sap.conn;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * SAP传参构建类
 *
 * @author zqz
 * @since 2023-04-12
 */
public class SapFunctionBuilder {
    private final JCoDestination destination;
    private final JCoFunction function;
    private final Log log = LogFactory.getLog(getClass());

    SapFunctionBuilder(String functionName, JCoDestination destination) {
        this.destination = destination;
        try {
            this.function = destination.getRepository().getFunction(functionName);
        } catch (Exception e) {
            throw new SAPConnectionException("无法获取到SAP接口：" + functionName, e);
        }
    }

    public SapFunctionBuilder addImportantParam(String key, Object value) {
        this.function.getImportParameterList().setValue(key, value);
        return this;
    }

    public <T> SapFunctionBuilder addTableParams(String key, List<T> list, BiConsumer<T, JCoTable> setter) {
        for (T t : list) {
            JCoTable table = this.function.getTableParameterList().getTable(key);
            table.appendRow();
            setter.accept(t, table);
        }
        return this;
    }

    public <T> SapFunctionBuilder addTableParams(String key, List<T> list, Class<T> tClass) {
        for (T t : list) {
            JCoTable table = this.function.getTableParameterList().getTable(key);
            table.appendRow();
            Map<String, ReflectProperties> mapping = SingleSapReflect.INSTANCE.sapReflect().mapping(tClass);
            mapping.forEach((k, v) -> {
                try {
                    table.setValue(v.sapFieldName(), v.getter().invoke(t));
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new SAPConnectionException("设置参数出错", e);
                }
            });
        }
        return this;
    }

    public String toXML() {
        return this.function.toXML();
    }

    public SapResponse execute() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("开始发起SAP请求，数据：" + toXML());
            }
            this.function.execute(this.destination);
            if (log.isDebugEnabled()) {
                log.debug("SAP请求结束，数据：" + toXML());
            }
            return new SapResponse(this.function);
        } catch (Exception e) {
            throw new SAPConnectionException("执行接口出错：" + this.function.getName(), e);
        }
    }

}
