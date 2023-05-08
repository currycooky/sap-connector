package com.curry.sap.conn;

import com.curry.sap.conn.mock.MockRepository;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * SAP响应转换测试
 *
 * @author zqz
 * @since 2023-04-13
 */
class SapResponseTest {
    @Test
    void testToList() throws JCoException {
        JCoDestination destination = mock(JCoDestination.class);
        JCoRepository repository = MockRepository.repository("/CURRYCOOKY.xml");
        when(destination.getRepository()).thenReturn(repository);
        SapResponse response = new SapFunctionBuilder("CURRYCOOKY", destination).execute();
        List<Head> heads = response.toList("O_HEAD", Head.class);
        Assertions.assertThat(heads).isNotEmpty().hasSize(1);
        Head head = heads.get(0);
        assertThat(head.getCode()).isEqualTo("A353");
        assertThat(head.getName()).isEqualTo("currycooky");
        assertThat(head.getWarehouse()).isEqualTo("自定义转换warehouse");
        assertThat(head.getNumber()).isEqualTo("63725046");
        assertThat(head.getText()).isEmpty();
    }

    @Test
    void testOutput() throws JCoException {
        JCoDestination destination = mock(JCoDestination.class);
        JCoRepository repository = MockRepository.repository("/CURRYCOOKY.xml");
        when(destination.getRepository()).thenReturn(repository);
        SapResponse response = new SapFunctionBuilder("CURRYCOOKY", destination).execute();
        Head output = response.output(Head.class);
        assertThat(output.getNumber()).isEqualTo(response.output("EBELN")).isEqualTo("63725046");
        assertThat(output.getWarehouse()).isEqualTo("自定义转换warehouse");
        assertThat(response.output("LGORT")).isEqualTo("warehouse");
    }
}
