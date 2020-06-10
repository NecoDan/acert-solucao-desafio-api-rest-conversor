package br.com.acert.api.conversor.temperaturas.controller;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.service.GeradorHistoricosService;
import br.com.acert.api.conversor.temperaturas.service.reports.HistoricoReportService;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import br.com.acert.api.conversor.temperaturas.util.StringUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HistoricoController.class)
class HistoricoControllerTest {

    private static final String PATH_PADRAO = "/historicos";
    private static final String URI = PATH_PADRAO + "/{action}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HistoricoReportService historicoReportService;

    @MockBean
    private GeradorHistoricosService geradorHistoricosService;

    @Test
    public void deveRetornarProducerJSONContendoUmaListaComTodosOsHistoricosComEndpoint() throws Exception {
        System.out.println("\n#TEST: deveRetornarProducerJSONContendoUmaListaComTodosOsHistoricosComEndpoint: ");

        // -- 01_Cenário
        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));

        // -- 02_Ação
        given(historicoReportService.listarTodos()).willReturn(historicoWrapper);
        ResultActions response = getResponseEntityEndPointsMethodGET(PATH_PADRAO, MediaType.APPLICATION_JSON);

        // -- 03_Verificação_Validação
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.historicos").exists())
                .andExpect(jsonPath("$.historicos[*].valorGrauOrigem").isNotEmpty());
        Assert.assertNotNull(response.andReturn().getResponse().getContentAsString());

        toStringEnd(response, MediaType.APPLICATION_JSON);
    }

    @Test
    public void deveRetornarProducerJSONContendoUmaListaComTodosOsHistoricosEmJSON() throws Exception {
        System.out.println("\n#TEST: deveRetornarProducerJSONContendoUmaListaComTodosOsHistoricosEmJSON: ");

        // -- 01_Cenário
        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));

        // -- 02_Ação
        given(historicoReportService.listarTodos()).willReturn(historicoWrapper);
        ResultActions response = getResponseEntityEndPointsMethodGET(PATH_PADRAO.concat("/exibeTodosEmJSON"), MediaType.APPLICATION_JSON);

        // -- 03_Verificação_Validação
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.historicos").exists())
                .andExpect(jsonPath("$.historicos[*].id").isNotEmpty());
        Assert.assertNotNull(response.andReturn().getResponse().getContentAsString());

        toStringEnd(response, MediaType.APPLICATION_JSON);
    }

    @Test
    public void deveRetornarProducerJSONContendoUmaListaComTodosOsHistoricosEmXML() throws Exception {
        System.out.println("\n#TEST: deveRetornarProducerJSONContendoUmaListaComTodosOsHistoricosEmXML: ");

        // -- 01_Cenário
        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));

        // -- 02_Ação
        given(historicoReportService.listarTodos()).willReturn(historicoWrapper);
        ResultActions response = getResponseEntityEndPointsMethodGET(PATH_PADRAO.concat("/exibeTodosEmXML"), MediaType.APPLICATION_XML);

        // -- 03_Verificação_Validação
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML));
        Assert.assertNotNull(response.andReturn().getResponse().getContentAsString());

        toStringEnd(response, MediaType.APPLICATION_XML);
    }

    @Test
    public void deveRetornarProducerJSONContendoUmaListaHistoricosFromData() throws Exception {
        System.out.println("\n#TEST: deveRetornarProducerJSONContendoUmaListaHistoricosFromData: ");

        // -- 01_Cenário
        LocalDate data = LocalDate.now();
        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));

        // -- 02_Ação
        given(historicoReportService.listarPorDataInsercao(data)).willReturn(historicoWrapper);
        String uri = PATH_PADRAO.concat("/buscaPorDataCadastro?data=").concat(StringUtil.formatLocalDate(data));
        ResultActions response = getResponseEntityEndPointsMethodGET(uri, MediaType.APPLICATION_JSON);

        // -- 03_Verificação_Validação
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.historicos").exists())
                .andExpect(jsonPath("$.historicos[*].data").isNotEmpty());
        Assert.assertNotNull(response.andReturn().getResponse().getContentAsString());

        toStringEnd(response, MediaType.APPLICATION_JSON);
    }

    @Test
    public void deveRetornarProducerJSONContendoUmaListaHistoricosFromPeriodo() throws Exception {
        System.out.println("\n#TEST: deveRetornarProducerJSONContendoUmaListaHistoricosFromPeriodo: ");

        // -- 01_Cenário
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = LocalDate.now();

        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));
        historicoWrapper.add(constroiHistoricoValido(true));

        // -- 02_Ação
        given(historicoReportService.listarPorPeriodo(dataInicio, dataFim)).willReturn(historicoWrapper);

        String uri = PATH_PADRAO.concat("/buscaPorPeriodo?dataInicio=" +
                StringUtil.formatLocalDate(dataInicio) +
                "&" +
                "dataFim=" +
                StringUtil.formatLocalDate(dataInicio));

        ResultActions response = getResponseEntityEndPointsMethodGET(uri, MediaType.APPLICATION_JSON);

        // -- 03_Verificação_Validação
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.historicos").exists())
                .andExpect(jsonPath("$.historicos[*].data").isNotEmpty());
        Assert.assertNotNull(response.andReturn().getResponse().getContentAsString());

        toStringEnd(response, MediaType.APPLICATION_JSON);
    }

    @Test
    public void deveRetornarProducerJSONContendoUmaListaHistoricosFromValorOrigemMenorOuIgual() throws Exception {
        System.out.println("\n#TEST: deveRetornarProducerJSONContendoUmaListaHistoricosFromValorOrigemMenorOuIgual: ");

        // -- 01_Cenário
        BigDecimal filterValorGrauOrigem = RandomicoUtil.gerarValorRandomicoDecimalAte(100);
        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();

        Historico h1 = constroiHistoricoValido(true);
        h1.setValorGrauOrigem(filterValorGrauOrigem);
        historicoWrapper.add(h1);

        Historico h2 = constroiHistoricoValido(true);
        h2.setValorGrauOrigem(filterValorGrauOrigem);
        historicoWrapper.add(h2);

        // -- 02_Ação
        given(historicoReportService.listarPorValorOrigemMenorOuIgualQue(filterValorGrauOrigem)).willReturn(historicoWrapper);
        String uri = PATH_PADRAO.concat("/buscaValorOrigemIgualOuMenor?valor=" + filterValorGrauOrigem.toString());
        ResultActions response = getResponseEntityEndPointsMethodGET(uri, MediaType.APPLICATION_JSON);

        // -- 03_Verificação_Validação
        response.andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.historicos").exists())
                .andExpect(jsonPath("$.historicos[*].valorGrauOrigem").isNotEmpty());
        Assert.assertNotNull(response.andReturn().getResponse().getContentAsString());

        toStringEnd(response, MediaType.APPLICATION_JSON);
    }

    private Historico constroiHistoricoValido(boolean preencheTodosAtributos) {
        EscalaTermometrica origem = EscalaTermometrica.builder().id(EscalaTermometrica.CELSIUS).pontoFusao(BigDecimal.valueOf(0)).build();
        EscalaTermometrica destino = EscalaTermometrica.builder().id(EscalaTermometrica.FAHRENHEIT).pontoFusao(BigDecimal.valueOf(32.00)).build();

        Historico historico = Historico.builder()
                .escalaTermometricaOrigem(origem)
                .escalaTermometricaDestino(destino)
                .valorGrauOrigem(RandomicoUtil.gerarValorRandomicoDecimalAte(40))
                .valorGrauResultado(RandomicoUtil.gerarValorRandomicoDecimalAte(100))
                .build();

        if (preencheTodosAtributos) {
            historico.setId(RandomicoUtil.gerarValorRandomicoLong());
            historico.gerarDataCorrente();
            historico.ativado();
        }

        return historico;
    }

    private ResultActions getResponseEntityEndPointsMethodGET(String url, MediaType mediaType) throws Exception {
        return this.mvc.perform(get(url).accept(mediaType));
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