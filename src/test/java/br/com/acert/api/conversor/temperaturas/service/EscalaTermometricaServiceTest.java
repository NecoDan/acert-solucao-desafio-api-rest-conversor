package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.repository.EscalaTermometricaRepository;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Slf4j
class EscalaTermometricaServiceTest {

    @Mock
    private EscalaTermometricaRepository escalaTermometricaRepository;
    @Spy
    @InjectMocks
    private EscalaTermometricaService escalaTermometricaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(escalaTermometricaRepository);
        reset(escalaTermometricaService);
    }

    @Test
    void deveRetornarEscalaTermometricaPorId() {
        log.info("{} ", "#TEST: deveRetornarEscalaTermometricaPorId: ");

        // -- 01_Cenário
        resetarMocks();
        Long id = RandomicoUtil.gerarValorRandomicoLong();

        // -- 02_Ação
        doCallRealMethod().when(escalaTermometricaService).recuperarPorId(any(Long.class));
        when(escalaTermometricaService.recuperarPorId(id)).thenReturn(Optional.of(new EscalaTermometrica()));

        // -- 03_Verificação_Validação
        Assertions.assertTrue(escalaTermometricaService.recuperarPorId(id).isPresent());
        log.info("{} ", "-------------------------------------------------------------");
    }
}