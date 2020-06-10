package br.com.acert.api.conversor.temperaturas.service.reports;

import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.repository.HistoricoRepository;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@Slf4j
class HistoricoReportServiceTest {

    @Mock
    private HistoricoRepository historicoRepository;

    @Spy
    @InjectMocks
    private HistoricoReportService historicoReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(historicoRepository);
        reset(historicoReportService);
    }

    @Test
    void deveRetornarHistoricoWrapperComUmHistoricoPorId() {
        log.info("{} ", "#TEST: deveRetornarHistoricoWrapperComUmHistoricoPorId: ");

        // -- 01_Cenário
        resetarMocks();
        Long id = RandomicoUtil.gerarValorRandomicoLong();
        HistoricoWrapper historicoWrapper = mock(HistoricoWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorId(isA(Long.class));
        when(historicoReportService.listarPorId(id)).thenReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        assertEquals(historicoWrapper, historicoReportService.listarPorId(id));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarHistoricoWrapperComUmaListaHistoricoPorValorOrigemMaiorOuIgualQue() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarHistoricoWrapperComUmaListaHistoricoPorValorOrigemMaiorOuIgualQue: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorOrigem = RandomicoUtil.gerarValorRandomicoDecimal();
        HistoricoWrapper historicoWrapper = mock(HistoricoWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorValorOrigemMaiorOuIgualQue(isA(BigDecimal.class));
        when(historicoReportService.listarPorValorOrigemMaiorOuIgualQue(valorOrigem)).thenReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        assertEquals(historicoWrapper, historicoReportService.listarPorValorOrigemMaiorOuIgualQue(valorOrigem));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarHistoricoWrapperComUmaListaHistoricoPorValorOrigemMenorOuIgualQue() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarHistoricoWrapperComUmaListaHistoricoPorValorOrigemMenorOuIgualQue: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorOrigem = RandomicoUtil.gerarValorRandomicoDecimal();
        HistoricoWrapper historicoWrapper = mock(HistoricoWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorValorOrigemMenorOuIgualQue(isA(BigDecimal.class));
        when(historicoReportService.listarPorValorOrigemMenorOuIgualQue(valorOrigem)).thenReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        assertEquals(historicoWrapper, historicoReportService.listarPorValorOrigemMenorOuIgualQue(valorOrigem));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarHistoricoWrapperComUmaListaHistoricoPorResultadoMaiorOuIgualQue() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarHistoricoWrapperComUmaListaHistoricoPorResultadoMaiorOuIgualQue: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorResultado = RandomicoUtil.gerarValorRandomicoDecimal();
        HistoricoWrapper historicoWrapper = mock(HistoricoWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorResultadoMaiorOuIgualQue(isA(BigDecimal.class));
        when(historicoReportService.listarPorResultadoMaiorOuIgualQue(valorResultado)).thenReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        assertEquals(historicoWrapper, historicoReportService.listarPorResultadoMaiorOuIgualQue(valorResultado));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarHistoricoWrapperComUmaListaHistoricoPorResultadoMenorOuIgualQue() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarHistoricoWrapperComUmaListaHistoricoPorResultadoMenorOuIgualQue: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorResultado = RandomicoUtil.gerarValorRandomicoDecimal();
        HistoricoWrapper historicoWrapper = mock(HistoricoWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorResultadoMenorOuIgualQue(isA(BigDecimal.class));
        when(historicoReportService.listarPorResultadoMenorOuIgualQue(valorResultado)).thenReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        assertEquals(historicoWrapper, historicoReportService.listarPorResultadoMenorOuIgualQue(valorResultado));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarHistoricoWrapperComUmaListaHistoricoRecuperadasPorIntervaloData() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarHistoricoWrapperComUmaListaHistoricoRecuperadasPorIntervaloData: ");

        // -- 01_Cenário
        resetarMocks();
        LocalDate dataInicial = LocalDate.now();
        LocalDate dataFinal = dataInicial.plusDays(30);
        HistoricoWrapper historicoWrapper = mock(HistoricoWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorPeriodo(isA(LocalDate.class), isA(LocalDate.class));
        when(historicoReportService.listarPorPeriodo(dataInicial, dataFinal)).thenReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        assertEquals(historicoWrapper, historicoReportService.listarPorPeriodo(dataInicial, dataFinal));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarHistoricoWrapperComUmaListaHistoricoRecuperadasPorData() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarHistoricoWrapperComUmaListaHistoricoRecuperadasPorData: ");

        // -- 01_Cenário
        resetarMocks();
        LocalDate data = LocalDate.now();
        HistoricoWrapper historicoWrapper = mock(HistoricoWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorDataInsercao(isA(LocalDate.class));
        when(historicoReportService.listarPorDataInsercao(data)).thenReturn(historicoWrapper);

        // -- 03_Verificação_Validação
        assertEquals(historicoWrapper, historicoReportService.listarPorDataInsercao(data));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorOrigemMaiorInvalido() throws ServiceException {
        log.info("{} ", "#TEST: deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorOrigemMaiorInvalido: ");

        // -- 01_Cenário
        resetarMocks();

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorValorOrigemMaiorOuIgualQue(isA(BigDecimal.class));
        ServiceException exception = assertThrows(ServiceException.class,
                () -> historicoReportService.listarPorValorOrigemMaiorOuIgualQue(null));

        // -- 03_Verificação_Validação
        assertTrue(exception.getMessage().contains("valor do origem"));
        log.info("{} ", "EXCEPTION: ".concat(exception.getMessage()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorOrigemMenorInvalido() throws ServiceException {
        log.info("{} ", "#TEST: deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorOrigemMenorInvalido: ");

        // -- 01_Cenário
        resetarMocks();

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorValorOrigemMenorOuIgualQue(isA(BigDecimal.class));
        ServiceException exception = assertThrows(ServiceException.class,
                () -> historicoReportService.listarPorValorOrigemMenorOuIgualQue(null));

        // -- 03_Verificação_Validação
        assertTrue(exception.getMessage().contains("valor do origem"));
        log.info("{} ", "EXCEPTION: ".concat(exception.getMessage()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorResultadoMaiorInvalido() throws ServiceException {
        log.info("{} ", "#TEST: deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorResultadoMaiorInvalido: ");

        // -- 01_Cenário
        resetarMocks();

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorResultadoMaiorOuIgualQue(isA(BigDecimal.class));
        ServiceException exception = assertThrows(ServiceException.class,
                () -> historicoReportService.listarPorResultadoMaiorOuIgualQue(null));

        // -- 03_Verificação_Validação
        assertTrue(exception.getMessage().contains("valor do resultado"));
        log.info("{} ", "EXCEPTION: ".concat(exception.getMessage()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorResultadoMenorInvalido() throws ServiceException {
        log.info("{} ", "#TEST: deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperPorValorResultadoMenorInvalido: ");

        // -- 01_Cenário
        resetarMocks();

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorResultadoMenorOuIgualQue(isA(BigDecimal.class));
        ServiceException exception = assertThrows(ServiceException.class,
                () -> historicoReportService.listarPorResultadoMenorOuIgualQue(null));

        // -- 03_Verificação_Validação
        assertTrue(exception.getMessage().contains("valor do resultado"));
        log.info("{} ", "EXCEPTION: ".concat(exception.getMessage()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperRecuperadasPorIntervaloInvalido() throws ServiceException {
        log.info("{} ", "#TEST: deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperRecuperadasPorIntervaloInvalido: ");

        // -- 01_Cenário
        resetarMocks();

        // -- 02_Ação
        doCallRealMethod().when(historicoReportService).listarPorPeriodo(isA(LocalDate.class), isA(LocalDate.class));
        ServiceException exception = assertThrows(ServiceException.class,
                () -> historicoReportService.listarPorPeriodo(null, null));

        // -- 03_Verificação_Validação
        assertTrue(exception.getMessage().contains("Data Inicial como filtro de busca"));
        log.info("{} ", "EXCEPTION: ".concat(exception.getMessage()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperRecuperadasPorDataInvalida() {
        log.info("{} ", "#TEST: deveLancarServiceExceptionAoTentarRetornarHistoricoWrapperRecuperadasPorDataInvalida: ");

        // -- 01_Cenário
        resetarMocks();
        String msgValidacaoExpection = "Parametro Data como filtro de busca, encontra-se inválida e/ou inexistente [NULL].";

        // -- 02_Ação & 03_Verificação_Validação
        assertThatThrownBy(() -> historicoReportService.listarPorDataInsercao(null))
                .isExactlyInstanceOf(ServiceException.class)
                .hasMessage(msgValidacaoExpection);

        log.info("{} ", "MSG_EXCEPTION: ".concat(msgValidacaoExpection));
        log.info("{} ", "-------------------------------------------------------------");
    }
}