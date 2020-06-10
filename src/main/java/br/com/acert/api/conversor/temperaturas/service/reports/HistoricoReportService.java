package br.com.acert.api.conversor.temperaturas.service.reports;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.repository.HistoricoRepository;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoricoReportService implements IHistoricoReportService {

    private final HistoricoRepository historicoRepository;

    @Override
    public HistoricoWrapper listarPorId(Long id) {
        Optional<Historico> historicoOptional = this.historicoRepository.findById(id);
        return historicoOptional.map(historico -> getWrapper(Collections.singletonList(historico))).orElse(getWrapper(Collections.emptyList()));
    }

    @Override
    public HistoricoWrapper listarTodos(Pageable pageable) {
        return getWrapper(this.historicoRepository.findAll(pageable).getContent());
    }

    @Override
    public HistoricoWrapper listarTodos() {
        return getWrapper(this.historicoRepository.findAll());
    }

    @Override
    public HistoricoWrapper listarPorValorOrigemMaiorOuIgualQue(BigDecimal valorOrigem) throws ServiceException {
        if (Objects.isNull(valorOrigem))
            throw new ServiceException("Parametro valor do origem como filtro de busca, encontra-se inválido e/ou inexistente [NULL].");
        return getWrapper(this.historicoRepository.findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemGreaterThanEqual(valorOrigem));
    }

    @Override
    public HistoricoWrapper listarPorValorOrigemMenorOuIgualQue(BigDecimal valorOrigem) throws ServiceException {
        if (Objects.isNull(valorOrigem))
            throw new ServiceException("Parametro valor do origem como filtro de busca, encontra-se inválido e/ou inexistente [NULL].");
        return getWrapper(this.historicoRepository.findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemLessThanEqual(valorOrigem));
    }

    @Override
    public HistoricoWrapper listarPorResultadoMaiorOuIgualQue(BigDecimal valorResultado) throws ServiceException {
        if (Objects.isNull(valorResultado))
            throw new ServiceException("Parametro valor do resultado como filtro de busca, encontra-se inválido e/ou inexistente [NULL].");
        return getWrapper(this.historicoRepository.findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoGreaterThanEqual(valorResultado));
    }

    @Override
    public HistoricoWrapper listarPorResultadoMenorOuIgualQue(BigDecimal valorResultado) throws ServiceException {
        if (Objects.isNull(valorResultado))
            throw new ServiceException("Parametro valor do resultado como filtro de busca, encontra-se inválido e/ou inexistente [NULL].");
        return getWrapper(this.historicoRepository.findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoLessThanEqual(valorResultado));
    }

    @Override
    public HistoricoWrapper listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws ServiceException {
        if (Objects.isNull(dataInicio))
            throw new ServiceException("Parametro Data Inicial como filtro de busca, encontra-se inválida e/ou inexistente [NULL].");

        if (Objects.isNull(dataFim))
            throw new ServiceException("Parametro Data Final como filtro de busca, encontra-se inválida e/ou inexistente [NULL].");

        return getWrapper(this.historicoRepository.recuperarTodosPorPeriodo(dataInicio, dataFim));
    }

    @Override
    public HistoricoWrapper listarPorDataInsercao(LocalDate data) throws ServiceException {
        if (Objects.isNull(data))
            throw new ServiceException("Parametro Data como filtro de busca, encontra-se inválida e/ou inexistente [NULL].");
        return getWrapper(this.historicoRepository.recuperarTodosPorDataInsercao(data));
    }

    private HistoricoWrapper getWrapper(List<Historico> historicoList) {
        return HistoricoWrapper.builder().historicos(historicoList).build();
    }
}
