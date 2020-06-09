package br.com.acert.api.conversor.temperaturas.repository;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
class EscalaTermometricaRepositoryTest {

    @Mock
    private EscalaTermometricaRepository escalaTermometricaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(escalaTermometricaRepository);
    }

    @Test
    void deveRetornarListaEscalaTermometricasAtivasOuInativasPorParametro() {
        log.info("{} ", "#TEST: deveRetornarListaEscalaTermometricasAtivasOuInativasPorParametro: ");

        // -- 01_Cenário
        resetarMocks();
        List<EscalaTermometrica> escalasTermometricas = Arrays.asList(mock(EscalaTermometrica.class), mock(EscalaTermometrica.class), mock(EscalaTermometrica.class));
        log.info("{} Escalas Termometricas MOCKADOS", escalasTermometricas.size());

        // -- 02_Ação
        when(escalaTermometricaRepository.findAllByAtivo(any(Boolean.class))).thenReturn(escalasTermometricas);

        // -- 03_Verificação_Validação
        assertEquals(escalasTermometricas, escalaTermometricaRepository.findAllByAtivo(true));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaEscalaTermometricasRecuperadasPorParamDescricao() {
        log.info("{} ", "#TEST: deveRetornarListaEscalaTermometricasRecuperadasPorParamDescricao: ");

        // -- 01_Cenário
        resetarMocks();
        String descricao = "Celsius";
        List<EscalaTermometrica> escalasTermometricas = Arrays.asList(mock(EscalaTermometrica.class), mock(EscalaTermometrica.class));
        log.info("{} Escalas Termometricas MOCKADOS", escalasTermometricas.size());

        // -- 02_Ação
        when(escalaTermometricaRepository.findAllByDescricaoContainingIgnoreCase(any(String.class))).thenReturn(escalasTermometricas);

        // -- 03_Verificação_Validação
        assertEquals(escalasTermometricas, escalaTermometricaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveRetornarListaEscalaTermometricasRecuperadasPorParamDataInsercao() {
        log.info("{} ", "#TEST: deveRetornarListaEscalaTermometricasRecuperadasPorParamDataInsercao: ");

        // -- 01_Cenário
        resetarMocks();
        LocalDate data = LocalDate.now();
        List<EscalaTermometrica> escalasTermometricas = Arrays.asList(mock(EscalaTermometrica.class), mock(EscalaTermometrica.class), mock(EscalaTermometrica.class),
                mock(EscalaTermometrica.class), mock(EscalaTermometrica.class), mock(EscalaTermometrica.class));
        log.info("{} Escalas Termometricas MOCKADOS", escalasTermometricas.size());

        // -- 02_Ação
        when(escalaTermometricaRepository.recuperarTodosPorDataInsercao(any(LocalDate.class))).thenReturn(escalasTermometricas);

        // -- 03_Verificação_Validação
        assertEquals(escalasTermometricas, escalaTermometricaRepository.recuperarTodosPorDataInsercao(data));
        log.info("{} ", "-------------------------------------------------------------");
    }
}