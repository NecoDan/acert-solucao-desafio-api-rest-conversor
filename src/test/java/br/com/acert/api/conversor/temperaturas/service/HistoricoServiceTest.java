package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.repository.HistoricoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.reset;

@Slf4j
class HistoricoServiceTest {

    @Mock
    private HistoricoRepository historicoRepository;

    @Spy
    @InjectMocks
    private HistoricoService historicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void resetarMocks() {
        reset(historicoRepository);
        reset(historicoService);
    }
}