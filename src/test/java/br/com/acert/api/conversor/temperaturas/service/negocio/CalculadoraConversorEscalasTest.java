package br.com.acert.api.conversor.temperaturas.service.negocio;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class CalculadoraConversorEscalasTest {

    private CalculadoraConversorEscalas calculadoraConversorEscalas;

    @BeforeEach
    void setUp() {
        this.calculadoraConversorEscalas = new CalculadoraConversorEscalas();
    }

    @Test
    void deveRetornarVerdadeAoValidarParamHistoricoInvalidoOuNulo() {
        log.info("{} ", "#TEST: deveRetornarVerdadeAoValidarParamHistoricoInvalidoOuNulo: ");

        // -- 02_Ação &&  03_Verificação_Validação
        assertTrue(this.calculadoraConversorEscalas.isParamInvalidos(null));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarVerdadeAoValidarHistoricoParamEscalaOrigemInvalida() {
        log.info("{} ", "#TEST: deveRetornarVerdadeAoValidarHistoricoParamEscalaOrigemInvalida: ");

        // -- 01_Cenário
        Historico historico = getHistorico(null, null, BigDecimal.ZERO, BigDecimal.ZERO);

        // -- 02_Ação &&  03_Verificação_Validação
        assertTrue(this.calculadoraConversorEscalas.isParamInvalidos(historico));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarVerdadeAoValidarHistoricoParamEscalaDestinoInvalida() {
        log.info("{} ", "#TEST: deveRetornarVerdadeAoValidarHistoricoParamEscalaDestinoInvalida: ");

        // -- 01_Cenário
        Historico historico = getHistorico(new EscalaTermometrica(), null, BigDecimal.ZERO, BigDecimal.ZERO);

        // -- 02_Ação &&  03_Verificação_Validação
        assertTrue(this.calculadoraConversorEscalas.isParamInvalidos(historico));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarVerdadeAoValidarHistoricoParamValorGrauOrigemInvalido() {
        log.info("{} ", "#TEST: deveRetornarVerdadeAoValidarHistoricoParamValorGrauOrigemInvalido: ");

        // -- 01_Cenário
        Historico historico = getHistorico(new EscalaTermometrica(), new EscalaTermometrica(), BigDecimal.ZERO, BigDecimal.ZERO);

        // -- 02_Ação &&  03_Verificação_Validação
        assertTrue(this.calculadoraConversorEscalas.isParamInvalidos(historico));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarVerdadeAoValidarHistoricoParamValorPontoFusaoEscalaOrigemInvalido() {
        log.info("{} ", "#TEST: deveRetornarVerdadeAoValidarHistoricoParamValorPontoFusaoEscalaOrigemInvalido: ");

        // -- 01_Cenário
        EscalaTermometrica origem = EscalaTermometrica.builder().id(EscalaTermometrica.CELSIUS).build();
        Historico historico = getHistorico(origem, new EscalaTermometrica(), RandomicoUtil.gerarValorRandomicoDecimal(), BigDecimal.ZERO);

        // -- 02_Ação &&  03_Verificação_Validação
        assertTrue(this.calculadoraConversorEscalas.isParamInvalidos(historico));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarVerdadeAoValidarHistoricoParamValorPontoFusaoEscalaDestinoInvalido() {
        log.info("{} ", "#TEST: deveRetornarVerdadeAoValidarHistoricoParamValorPontoFusaoEscalaDestinoInvalido: ");

        // -- 01_Cenário
        EscalaTermometrica origem = EscalaTermometrica.builder().id(EscalaTermometrica.CELSIUS).pontoFusao(BigDecimal.valueOf(0)).build();
        EscalaTermometrica destino = EscalaTermometrica.builder().id(EscalaTermometrica.FAHRENHEIT).build();
        Historico historico = getHistorico(origem, destino, RandomicoUtil.gerarValorRandomicoDecimal(), BigDecimal.ZERO);

        // -- 02_Ação &&  03_Verificação_Validação
        assertTrue(this.calculadoraConversorEscalas.isParamInvalidos(historico));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveConverterAPartirHistoricoOrigemCelsiusParaFahrenheit() {
        log.info("{} ", "#TEST: deveConverterAPartirHistoricoOrigemCelsiusParaFahrenheit: ");

        // -- 01_Cenário
        BigDecimal valorGrauOrigem = RandomicoUtil.gerarValorRandomicoDecimalAte(40);
        EscalaTermometrica origem = EscalaTermometrica.builder().id(EscalaTermometrica.CELSIUS).pontoFusao(BigDecimal.valueOf(0)).build();
        EscalaTermometrica destino = EscalaTermometrica.builder().id(EscalaTermometrica.FAHRENHEIT).pontoFusao(BigDecimal.valueOf(32.00)).build();
        Historico historico = getHistorico(origem, destino, valorGrauOrigem, BigDecimal.ZERO);

        // -- 02_Ação
        BigDecimal valorResuladoFahrenheit = this.calculadoraConversorEscalas.converterAPartirHistorico(historico);

        // -- 03_Verificação_Validação
        assertTrue(valorResuladoFahrenheit != null && (valorResuladoFahrenheit.doubleValue() > 0 || valorResuladoFahrenheit.doubleValue() < 0));
        log.info("{} ", "GRAU_ORIGEM - CELSIUS: ".concat(valorGrauOrigem.toString()));
        log.info("{} ", "GRAU_CONVERTIDO - FAHRENHEIT: ".concat(valorResuladoFahrenheit.toString()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveConverterAPartirHistoricoOrigemFahrenheitParaCelsius() {
        log.info("{} ", "#TEST: deveConverterAPartirHistoricoOrigemFahrenheitParaCelsius: ");

        // -- 01_Cenário
        BigDecimal valorGrauOrigem = RandomicoUtil.gerarValorRandomicoDecimalAte(100);
        EscalaTermometrica origem = EscalaTermometrica.builder().id(EscalaTermometrica.FAHRENHEIT).pontoFusao(BigDecimal.valueOf(32.00)).build();
        EscalaTermometrica destino = EscalaTermometrica.builder().id(EscalaTermometrica.CELSIUS).pontoFusao(BigDecimal.valueOf(0)).build();
        Historico historico = getHistorico(origem, destino, valorGrauOrigem, BigDecimal.ZERO);

        // -- 02_Ação
        BigDecimal valorResuladoCelsius = this.calculadoraConversorEscalas.converterAPartirHistorico(historico);

        // -- 03_Verificação_Validação
        assertTrue(valorResuladoCelsius != null && (valorResuladoCelsius.doubleValue() > 0 || valorResuladoCelsius.doubleValue() < 0));
        log.info("{} ", "GRAU_ORIGEM - CELSIUS: ".concat(valorGrauOrigem.toString()));
        log.info("{} ", "GRAU_CONVERTIDO - FAHRENHEIT: ".concat(valorResuladoCelsius.toString()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    private Historico getHistorico(EscalaTermometrica escalaTermometricaOrigem, EscalaTermometrica escalaTermometricaDestino,
                                   BigDecimal valorGrauOrigem, BigDecimal valorGrauResultado) {
        return Historico.builder()
                .escalaTermometricaOrigem(escalaTermometricaOrigem)
                .escalaTermometricaDestino(escalaTermometricaDestino)
                .valorGrauOrigem(valorGrauOrigem)
                .valorGrauResultado(valorGrauResultado)
                .build();
    }
}