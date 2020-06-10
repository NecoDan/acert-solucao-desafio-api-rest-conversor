package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.service.negocio.TipoCalculoConversao;
import br.com.acert.api.conversor.temperaturas.service.strategy.IBuilderCalculoConversorService;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;
import br.com.acert.api.conversor.temperaturas.validation.IHistoricoValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeradorHistoricosService implements IGeradorHistoricosService {

    private final IHistoricoService historicoService;
    private final IHistoricoValidation historicoValidation;
    private final IEscalaTermometricaService escalaTermometricaService;
    private final IBuilderCalculoConversorService builderCalculoConversorService;

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public HistoricoWrapper gerar(List<Historico> historicos) throws ValidadorException {
        if (Objects.isNull(historicos) || historicos.isEmpty())
            throw new ValidadorException("Não existem historicos(s) ou historico(s) encontram-se inexistente(s) a serem processados pela API.");

        HistoricoWrapper historicoWrapper = HistoricoWrapper.builder().build();

        for (Historico historico : historicos)
            historicoWrapper.add(gerar(historico));
        return historicoWrapper;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public Historico gerar(Historico historico) throws ValidadorException {
        this.historicoValidation.validar(historico);

        Historico historicoNovo = Historico.builder().build();
        Optional<Historico> optionalHistorico = (Objects.isNull(historico.getId())) ? Optional.of(historico) : getHistoricoExistenteRetornaNovoHistorico(historico);

        if (optionalHistorico.isPresent())
            historicoNovo = save(optionalHistorico.get());

        return historicoNovo;
    }

    @Override
    @Transactional(value = Transactional.TxType.REQUIRED)
    public Historico save(Historico historico) throws ValidadorException {
        carregarDependencias(historico);
        return this.historicoService.salvar(historico);
    }

    private Optional<Historico> getHistoricoExistenteRetornaNovoHistorico(Historico historico) {
        Long id = historico.getId();

        Optional<Historico> optHistoricoExistente = this.historicoService.recuperarPorId(id);
        Historico historicoNovo = (optHistoricoExistente.isPresent()) ? mountHistoricoFromOther(optHistoricoExistente.get()) : mountHistoricoFromOther(historico);
        return Optional.of(historicoNovo);
    }

    private Historico mountHistoricoFromOther(Historico historico) {
        return Historico.builder()
                .escalaTermometricaOrigem(historico.getEscalaTermometricaOrigem())
                .escalaTermometricaDestino(historico.getEscalaTermometricaDestino())
                .valorGrauOrigem(historico.getValorGrauOrigem())
                .build();
    }

    private void carregarDependencias(Historico historico) throws ValidadorException {
        Optional<EscalaTermometrica> escalaTermometricaOrigemOptional = this.escalaTermometricaService.recuperarPorId(historico.getEscalaTermometricaOrigem().getId());
        escalaTermometricaOrigemOptional.ifPresent(historico::setEscalaTermometricaOrigem);

        Optional<EscalaTermometrica> escalaTermometricaDestinoOptional = this.escalaTermometricaService.recuperarPorId(historico.getEscalaTermometricaDestino().getId());
        escalaTermometricaDestinoOptional.ifPresent(historico::setEscalaTermometricaDestino);

        this.historicoValidation.validarEscalas(historico);

        TipoCalculoConversao tipoCalculoConversao = builderCalculoConversorService.obterTipoCalculoConversorAPartir(historico);
        historico.setTipoCalculoConversao(tipoCalculoConversao);

        historico.calcularResultado();
        historico.gerarDataCorrente();
        historico.ativado();
    }
}
