package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.repository.HistoricoRepository;
import br.com.acert.api.conversor.temperaturas.util.RandomicoUtil;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;
import br.com.acert.api.conversor.temperaturas.validation.HistoricoValidation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@Slf4j
class HistoricoServiceTest {

    @Mock
    private HistoricoRepository historicoRepository;
    @Mock
    private HistoricoValidation historicoValidation;
    @Spy
    @InjectMocks
    private HistoricoService historicoServiceMock;

    private HistoricoService historicoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.historicoService = new HistoricoService(historicoRepository, new HistoricoValidation());
    }

    private void resetarMocks() {
        reset(historicoValidation);
        reset(historicoRepository);
        reset(historicoServiceMock);
    }

    @Test
    public void deveSalvarComSucessoUmHistoricoValido() throws ValidadorException {
        log.info("{} ", "#TEST: deveSalvarComSucessoUmHistoricoValido: ");

        // -- 01_Cenário
        resetarMocks();
        BigDecimal valorGrauOrigem = RandomicoUtil.gerarValorRandomicoDecimalAte(40);
        EscalaTermometrica origem = EscalaTermometrica.builder().id(EscalaTermometrica.CELSIUS).pontoFusao(BigDecimal.valueOf(0)).build();
        EscalaTermometrica destino = EscalaTermometrica.builder().id(EscalaTermometrica.FAHRENHEIT).pontoFusao(BigDecimal.valueOf(32.00)).build();
        Historico historico = getHistorico(origem, destino, valorGrauOrigem);

        // -- 02_Ação
        doCallRealMethod().when(historicoServiceMock).salvar(isA(Historico.class));
        historicoServiceMock.salvar(historico);

        // -- 03_Verificação_Validação
        verify(historicoServiceMock).salvar(any(Historico.class));
        log.info("{} ", "-------------------------------------------------------------");
    }

    @Test
    void deveLancarServiceExceptionAoTentarSalvarHistoricoInvalido() {
        log.info("{} ", "#TEST: deveLancarServiceExceptionAoTentarSalvarHistoricoInvalido: ");

        // -- 01_Cenário
        String strMensagemValidacaoException = "Nenhum historico encontrado";
        resetarMocks();

        // -- 02_Ação
        ValidadorException exception = assertThrows(ValidadorException.class, () -> historicoService.salvar(null));

        // -- 03_Verificação_Validação
        assertTrue(exception.getMessage().contains(strMensagemValidacaoException));
        log.info("{} ", "EXCEPTION: ".concat(exception.getMessage()));
        log.info("{} ", "-------------------------------------------------------------");
    }

    private Historico getHistorico(EscalaTermometrica escalaTermometricaOrigem, EscalaTermometrica escalaTermometricaDestino,
                                   BigDecimal valorGrauOrigem) {
        return Historico.builder()
                .escalaTermometricaOrigem(escalaTermometricaOrigem)
                .escalaTermometricaDestino(escalaTermometricaDestino)
                .valorGrauOrigem(valorGrauOrigem)
                .valorGrauResultado(BigDecimal.ZERO)
                .build();
    }
}