package br.com.acert.api.conversor.temperaturas.service.reports;

import br.com.acert.api.conversor.temperaturas.model.dtos.EscalaTermometricaWrapper;
import br.com.acert.api.conversor.temperaturas.repository.EscalaTermometricaRepository;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
class EscalaTermometricaReportServiceTest {

    @Mock
    private EscalaTermometricaRepository escalaTermometricaRepository;

    @Spy
    @InjectMocks
    private EscalaTermometricaReportService escalaTermometricaReportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(escalaTermometricaRepository);
        reset(escalaTermometricaReportService);
    }

    @Test
    void deveRetornarEscalaWrapperComUmaEscalaTermometricaPorId() {
        log.info("{} ", "#TEST: deveRetornarEscalaWrapperComUmaEscalaTermometricaPorId: ");

        // -- 01_Cenário
        resetarMocks();
        Long id = RandomicoUtil.gerarValorRandomicoLong();
        EscalaTermometricaWrapper escalaTermometricaWrapper = mock(EscalaTermometricaWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(escalaTermometricaReportService).listarPorId(isA(Long.class));
        when(escalaTermometricaReportService.listarPorId(id)).thenReturn(escalaTermometricaWrapper);

        // -- 03_Verificação_Validação
        assertEquals(escalaTermometricaWrapper, escalaTermometricaReportService.listarPorId(id));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarEscalaWrapperComUmaListaEscalasTermometricasValidas() {
        log.info("{} ", "#TEST: deveRetornarEscalaWrapperComUmaListaEscalasTermometricasValidas: ");

        // -- 01_Cenário
        resetarMocks();
        EscalaTermometricaWrapper escalaTermometricaWrapper = mock(EscalaTermometricaWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(escalaTermometricaReportService).listarTodos();
        when(escalaTermometricaReportService.listarTodos()).thenReturn(escalaTermometricaWrapper);

        // -- 03_Verificação_Validação
        assertEquals(escalaTermometricaWrapper, escalaTermometricaReportService.listarTodos());
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarEscalaWrapperComUmaListaEscalasTermometricasSomenteAtivas() {
        log.info("{} ", "#TEST: deveRetornarEscalaWrapperComUmaListaEscalasTermometricasSomenteAtivas: ");

        // -- 01_Cenário
        resetarMocks();
        EscalaTermometricaWrapper escalaTermometricaWrapper = mock(EscalaTermometricaWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(escalaTermometricaReportService).listarAtivos();
        when(escalaTermometricaReportService.listarAtivos()).thenReturn(escalaTermometricaWrapper);

        // -- 03_Verificação_Validação
        assertEquals(escalaTermometricaWrapper, escalaTermometricaReportService.listarAtivos());
        log.info("{} ", "-------------------------------------------------------------");
    }


    @Test
    void deveRetornarEscalaWrapperComUmaListaEscalasTermometricasSomenteInativas() {
        log.info("{} ", "#TEST: deveRetornarEscalaWrapperComUmaListaEscalasTermometricasSomenteInativas: ");

        // -- 01_Cenário
        resetarMocks();
        EscalaTermometricaWrapper escalaTermometricaWrapper = mock(EscalaTermometricaWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(escalaTermometricaReportService).listarInativos();
        when(escalaTermometricaReportService.listarInativos()).thenReturn(escalaTermometricaWrapper);

        // -- 03_Verificação_Validação
        assertEquals(escalaTermometricaWrapper, escalaTermometricaReportService.listarInativos());
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarEscalaWrapperComUmaListaEscalasTermometricasRecuperadasPelaDescricao() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarEscalaWrapperComUmaListaEscalasTermometricasRecuperadasPelaDescricao: ");

        // -- 01_Cenário
        resetarMocks();
        String descricao = String.valueOf(RandomicoUtil.gerarValorRandomicoLong());
        EscalaTermometricaWrapper escalaTermometricaWrapper = mock(EscalaTermometricaWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(escalaTermometricaReportService).listarPorDescricao(isA(String.class));
        when(escalaTermometricaReportService.listarPorDescricao(descricao)).thenReturn(escalaTermometricaWrapper);

        // -- 03_Verificação_Validação
        assertEquals(escalaTermometricaWrapper, escalaTermometricaReportService.listarPorDescricao(descricao));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarEscalaWrapperComUmaListaEscalasTermometricasRecuperadasPorData() throws ServiceException {
        log.info("{} ", "#TEST: deveRetornarEscalaWrapperComUmaListaEscalasTermometricasRecuperadasPorData: ");

        // -- 01_Cenário
        resetarMocks();
        LocalDate data = LocalDate.now();
        EscalaTermometricaWrapper escalaTermometricaWrapper = mock(EscalaTermometricaWrapper.class);

        // -- 02_Ação
        doCallRealMethod().when(escalaTermometricaReportService).listarPorDataInsercao(isA(LocalDate.class));
        when(escalaTermometricaReportService.listarPorDataInsercao(data)).thenReturn(escalaTermometricaWrapper);

        // -- 03_Verificação_Validação
        assertEquals(escalaTermometricaWrapper, escalaTermometricaReportService.listarPorDataInsercao(data));
        log.info("{} ", "-------------------------------------------------------------");
    }
}