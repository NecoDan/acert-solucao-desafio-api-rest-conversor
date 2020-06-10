package br.com.acert.api.conversor.temperaturas.service.negocio;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@Slf4j
class CalculadoraConversorEscalasTest {

    @Mock
    private CalculadoraConversorEscalas calculadoraConversorEscalas;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(calculadoraConversorEscalas);
    }

    @Test
    void deveConverterAPartirHistoricoOrigemCelsiusParaKelvin() {
        log.info("{} ", "#TEST: deveConverterAPartirHistoricoOrigemCelsiusParaKelvin: ");

        // -- 01_Cenário
        resetarMocks();

//        // -- 02_Ação
//        doCallRealMethod().when(historicoReportService).listarPorId(isA(Long.class));
//        when(historicoReportService.listarPorId(id)).thenReturn(historicoWrapper);
//
//        // -- 03_Verificação_Validação
//        assertEquals(historicoWrapper, historicoReportService.listarPorId(id));

        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarVerdadePorParametrosHistoricoInvalidos() {
        log.info("{} ", "#TEST: deveRetornarVerdadePorParametrosHistoricoInvalidos: ");

        // -- 01_Cenário
        resetarMocks();

        // -- 02_Ação
        doCallRealMethod().when(calculadoraConversorEscalas).isParamInvalidos(isA(Historico.class));
        when(calculadoraConversorEscalas.isParamInvalidos(null)).thenReturn(false);

        // -- 03_Verificação_Validação
        assertFalse(calculadoraConversorEscalas.isParamInvalidos(null));
        log.info("{} ", "-------------------------------------------------------------");
    }
}