package br.com.acert.api.conversor.temperaturas.repository;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
class HistoricoRepositoryTest {

    @Mock
    private HistoricoRepository historicoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(historicoRepository);
    }

    @Test
    void deveRetornarListaHistoricoPorEscalaTermometricaOrigem() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorEscalaTermometricaOrigem: ");

        // -- 01_Cenário
        resetarMocks();
        EscalaTermometrica escalaTermometricaOrigem = new EscalaTermometrica();
        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.recuperarTodosPorEscalaTermometricaOrigem(any(EscalaTermometrica.class))).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.recuperarTodosPorEscalaTermometricaOrigem(escalaTermometricaOrigem));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaHistoricoPorEscalaTermometricaDestino() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorEscalaTermometricaDestino: ");

        // -- 01_Cenário
        resetarMocks();
        EscalaTermometrica escalaTermometricaDestino = new EscalaTermometrica();
        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.recuperarTodosPorEscalaTermometricaDestino(any(EscalaTermometrica.class))).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.recuperarTodosPorEscalaTermometricaDestino(escalaTermometricaDestino));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaHistoricoPorParamDataInsercao() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorParamDataInsercao: ");

        // -- 01_Cenário
        resetarMocks();
        LocalDate data = LocalDate.now();
        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class),
                mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.recuperarTodosPorDataInsercao(any(LocalDate.class))).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.recuperarTodosPorDataInsercao(data));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaHistoricoPorParamPeriodoDatas() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorParamPeriodoDatas: ");

        // -- 01_Cenário
        resetarMocks();
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.plusDays(30);

        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class),
                mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.recuperarTodosPorPeriodo(dataInicial, dataFinal)).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.recuperarTodosPorPeriodo(dataInicial, dataFinal));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaHistoricoPorParamResultadoNaoNuloEMenorOuIgual() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorParamResultadoNaoNuloEMenorOuIgual: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorMenorResultado = RandomicoUtil.gerarValorRandomicoDecimal();

        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoLessThanEqual(valorMenorResultado)).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoLessThanEqual(valorMenorResultado));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaHistoricoPorParamResultadoNaoNuloEMaiorOuIgual() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorParamResultadoNaoNuloEMaiorOuIgual: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorMaiorResultado = RandomicoUtil.gerarValorRandomicoDecimal();

        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoGreaterThanEqual(valorMaiorResultado)).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoGreaterThanEqual(valorMaiorResultado));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaHistoricoPorParamValorOrigemNaoNuloEMenorOuIgual() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorParamValorOrigemNaoNuloEMenorOuIgual: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorMenorOrigem = RandomicoUtil.gerarValorRandomicoDecimal();

        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemLessThanEqual(valorMenorOrigem)).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemLessThanEqual(valorMenorOrigem));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaHistoricoPorParamValorOrigemNaoNuloEMaiorOuIgual() {
        log.info("{} ", "#TEST: deveRetornarListaHistoricoPorParamValorOrigemNaoNuloEMenorOuIgual: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorMaiorOrigem = RandomicoUtil.gerarValorRandomicoDecimal();

        List<Historico> historicoList = Arrays.asList(mock(Historico.class), mock(Historico.class), mock(Historico.class), mock(Historico.class), mock(Historico.class));
        log.info("{} Historico conversões MOCKADOS", historicoList.size());

        // -- 02_Ação
        when(historicoRepository.findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemGreaterThanEqual(valorMaiorOrigem)).thenReturn(historicoList);

        // -- 03_Verificação_Validação
        assertEquals(historicoList, historicoRepository.findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemGreaterThanEqual(valorMaiorOrigem));
        log.info("{} ", "-------------------------------------------------------------");
    }
}