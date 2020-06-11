package br.com.acert.api.conversor.temperaturas.controller;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.service.GeradorHistoricosService;
import br.com.acert.api.conversor.temperaturas.service.reports.HistoricoReportService;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static reactor.core.publisher.Mono.when;

@RunWith(SpringRunner.class)
@WebFluxTest(HistoricoController.class)
public class HistoricoControllerPostTest {

    private static final String PATH_PADRAO = "/historicos";
    private static final String URI = PATH_PADRAO + "/{action}";

    @MockBean
    private HistoricoReportService historicoReportService;

    @MockBean
    private GeradorHistoricosService geradorHistoricosService;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void deveRetornarProducerJSONContendoUmUnicoHistoricoPorIdMethodGET() {
        System.out.println("\n#TEST: deveRetornarProducerJSONContendoUmUnicoHistoricoPorIdMethodGET: ");

        // -- 01_Cenário
        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();
        Long id = RandomicoUtil.gerarValorRandomicoLong();
        BigDecimal valorGrauOrigem = RandomicoUtil.gerarValorRandomicoDecimalAte(40);

        Historico historico = constroiHistoricoValido(true);
        historico.setId(id);
        historico.setValorGrauOrigem(valorGrauOrigem);
        historicoWrapper.add(historico);

        // -- 02_Ação
        given(historicoReportService.listarPorId(id)).willReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        String uri = PATH_PADRAO.concat("/").concat(String.valueOf(id));
        webTestClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(HistoricoWrapper.class)
                .isEqualTo(historicoWrapper);
    }

    @Test
    public void deveRetornarProducerJSONHistoricosAPartirConsumerJSONHistoricoPorMethodPOST() throws ValidadorException {
        System.out.println("\n#TEST: deveRetornarProducerJSONHistoricosAPartirConsumerJSONHistoricoPorMethodPOST: ");

        // -- 01_Cenário
        Long id = RandomicoUtil.gerarValorRandomicoLong();
        BigDecimal valorGrauOrigem = RandomicoUtil.gerarValorRandomicoDecimalAte(40);

        Historico historico = constroiHistoricoValido(true);
        historico.setId(id);
        historico.setValorGrauOrigem(valorGrauOrigem);

        // -- 02_Ação
//        Mono<Historico> historicoMono = Mono.just(historico);
//        when(geradorHistoricosService.gerar(historico)).thenReturn(historicoMono);

        webTestClient.post()
                .uri(PATH_PADRAO)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(historico), Historico.class)
                .exchange()
                .expectStatus()
                .isCreated();
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
}
