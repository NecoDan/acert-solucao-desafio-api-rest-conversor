package br.com.acert.api.conversor.temperaturas;

import br.com.acert.api.conversor.temperaturas.util.StringUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
class AppTests {
    @Test
    void contextLoads() {
    }


    private void toStringEnd(ResultActions response, MediaType mediaType) throws Exception {
        String result = response.andReturn().getResponse().getContentAsString();
        String out = "";

        if (mediaType == MediaType.APPLICATION_JSON)
            out = StringUtil.formatConteudoJSONFrom(result);

        if (mediaType == MediaType.APPLICATION_XML)
            out = StringUtil.formatConteudoXMLFrom(result);

        System.out.println("#TEST_RESULT: ".concat(out));
        System.out.println("-------------------------------------------------------------");
    }
}
