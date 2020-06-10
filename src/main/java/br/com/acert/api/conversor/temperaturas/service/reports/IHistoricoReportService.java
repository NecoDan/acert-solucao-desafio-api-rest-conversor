package br.com.acert.api.conversor.temperaturas.service.reports;

import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ServiceException;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IHistoricoReportService {

    HistoricoWrapper listarPorId(Long id);

    HistoricoWrapper listarTodos(Pageable pageable);

    HistoricoWrapper listarTodos();

    HistoricoWrapper listarPorValorOrigemMaiorOuIgualQue(BigDecimal valorOrigem) throws ServiceException;

    HistoricoWrapper listarPorValorOrigemMenorOuIgualQue(BigDecimal valorOrigem) throws ServiceException;

    HistoricoWrapper listarPorResultadoMaiorOuIgualQue(BigDecimal valorResultado) throws ServiceException;

    HistoricoWrapper listarPorResultadoMenorOuIgualQue(BigDecimal valorResultado) throws ServiceException;

    HistoricoWrapper listarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) throws ServiceException;

    HistoricoWrapper listarPorDataInsercao(LocalDate data) throws ServiceException;
}
