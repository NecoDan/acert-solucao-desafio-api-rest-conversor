package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.service.strategy.BuilderCalculoConversorService;
import br.com.acert.api.conversor.temperaturas.validation.HistoricoValidation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.reset;

@Slf4j
class GeradorHistoricosServiceTest {

    @Mock
    private HistoricoService historicoService;
    @Mock
    private HistoricoValidation historicoValidation;
    @Mock
    private EscalaTermometricaService escalaTermometricaService;
    @Mock
    private BuilderCalculoConversorService builderCalculoConversorService;

    @Spy
    @InjectMocks
    private GeradorHistoricosService geradorHistoricosServiceMock;

    private GeradorHistoricosService geradorHistoricosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.geradorHistoricosService = new GeradorHistoricosService(historicoService, new HistoricoValidation(), escalaTermometricaService, builderCalculoConversorService);
    }

    private void resertMocks() {
        reset(historicoService);
    }

    @Test
    void gerar() {
    }

    @Test
    void testGerar() {
    }
}