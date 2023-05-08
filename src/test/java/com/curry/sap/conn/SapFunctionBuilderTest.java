package com.curry.sap.conn;

import com.sap.conn.jco.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * SAP请求的Function构造测试
 *
 * @author zqz
 * @since 2023-04-12
 */
class SapFunctionBuilderTest {

    JCoDestination destination;
    JCoRepository repository;
    JCoFunction function;

    @BeforeEach
    void setUp() throws JCoException {
        destination = mock(JCoDestination.class);
        repository = mock(JCoRepository.class);
        function = mock(JCoFunction.class);
        when(destination.getRepository()).thenReturn(repository);
        when(repository.getFunction(anyString())).thenReturn(function);
    }

    @Test
    void testCreateNewFunctionBuilder() {
        String functionName = "functionName";

        SapFunctionBuilder sapFunctionBuilder = new SapFunctionBuilder(functionName, destination);
        assertThat(sapFunctionBuilder).isNotNull();
    }

    @Test
    void testAddImportantParam() {
        JCoParameterList parameterList = mock(JCoParameterList.class);
        when(function.getImportParameterList()).thenReturn(parameterList);
        SapFunctionBuilder functionBuilder = new SapFunctionBuilder("", destination);
        functionBuilder.addImportantParam("key", "value").addImportantParam("key2", 2);
        verify(function, times(2)).getImportParameterList();
        verify(parameterList).setValue("key", (Object) "value");
        verify(parameterList).setValue("key2", (Object) 2);
    }

    @Test
    void testAddTableParam() {
        JCoParameterList parameterList = mock(JCoParameterList.class);
        JCoTable table = mock(JCoTable.class);
        when(parameterList.getTable(anyString())).thenReturn(table);
        when(function.getTableParameterList()).thenReturn(parameterList);
        SapFunctionBuilder builder = new SapFunctionBuilder("", destination);
        List<String> list = Arrays.asList("workshop", "replies", "andy");
        builder.addTableParams("key", list, (e, t) -> t.setValue("word", e)).execute();
        verify(function, times(list.size())).getTableParameterList();
        verify(table, times(list.size())).appendRow();
        verify(table).setValue("word", list.get(0));
        verify(table).setValue("word", list.get(1));
        verify(table).setValue("word", list.get(2));
    }

    @Test
    void testAddTableParamAndUseClass() {
        JCoParameterList parameterList = mock(JCoParameterList.class);
        JCoTable table = mock(JCoTable.class);
        when(parameterList.getTable(anyString())).thenReturn(table);
        when(function.getTableParameterList()).thenReturn(parameterList);
        SapFunctionBuilder builder = new SapFunctionBuilder("", destination);
        List<SapTableParamModel> list = Arrays.asList(new SapTableParamModel("Ayasha"), new SapTableParamModel("Wilhelmina"));
        builder.addTableParams("", list, SapTableParamModel.class).execute();
        verify(function, times(list.size())).getTableParameterList();
        verify(table, times(list.size())).appendRow();
        verify(table).setValue("NAME", (Object) list.get(0).getName());
        verify(table).setValue("NAME", (Object) list.get(1).getName());
    }
}

