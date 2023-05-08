package com.curry.sap.conn.mock;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoRepository;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * 模拟Repository
 *
 * @author zqz
 * @since 2023-04-13
 */
public class MockRepository {

    public static JCoRepository repository(String filePath) {
        InputStream inputStream = MockRepository.class.getResourceAsStream(filePath);
        SAXReader saxReader = new SAXReader();
        Document document;
        try {
            document = saxReader.read(inputStream);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        Element root = document.getRootElement();

        try {
            JCoFunction function = mock(JCoFunction.class);
            JCoParameterList tableParameter = mockTableParameter(root);
            when(function.getTableParameterList()).thenReturn(tableParameter);
            JCoParameterList exportParameter = mockExportParameter(root);
            when(function.getExportParameterList()).thenReturn(exportParameter);
            JCoRepository repository = mock(JCoRepository.class);
            when(repository.getFunction(root.getName())).thenReturn(function);
            return repository;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static JCoParameterList mockExportParameter(Element root) {
        Map<String, Object> map = new HashMap<>();
        for (Element element : root.element("OUTPUT").elements()) {
            map.put(element.getName(), element.getTextTrim());
        }
        return new MockExportParameter(map);
    }

    private static JCoParameterList mockTableParameter(Element root) {
        JCoParameterList parameterList = mock(JCoParameterList.class);
        Element tables = root.element("TABLES");
        // 解析不同的表参数名
        for (Element element : tables.elements()) {
            // 一个表下可能存在多个item，一个item表示一条数据
            List<Map<String, Object>> list = new ArrayList<>();
            for (Element item : element.elements("item")) {
                Map<String, Object> map = new HashMap<>();
                // 解析每个item里的内容，保存到map中
                for (Element data : item.elements()) {
                    map.put(data.getName(), data.getTextTrim());
                }
                list.add(map);
            }
            when(parameterList.getTable(element.getName())).thenReturn(new MockJCoTable(list));
        }
        return parameterList;
    }
}
