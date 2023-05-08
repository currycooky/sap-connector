package com.curry.sap.conn;

import com.sap.conn.jco.JCoDestinationManager;

/**
 * 示例代码
 *
 * @author zqz
 * @since 2023-04-13
 */
@SuppressWarnings("all")
public class Sample {
    public void basic() {
        System.out.println(SapTemplate.connect().function("function").execute().toXML());;
    }

    public void executeMultipleFunctionUseOneTemplate() {
        SapTemplate template = SapTemplate.connect("default");
        SapResponse function1 = template.function("function1").execute();
        SapResponse function2 = template.function("function2").execute();
    }

    public void connectWithOtherWay() {
        SapTemplate template = SapTemplate.connect("default", JCoDestinationManager::getDestination);
        SapTemplate.connect(JCoDestinationManager::getDestination).function("function");
        // SapTemplate template = SapTemplate.connect("default", name -> SapUtil.connect(name));
    }
}
