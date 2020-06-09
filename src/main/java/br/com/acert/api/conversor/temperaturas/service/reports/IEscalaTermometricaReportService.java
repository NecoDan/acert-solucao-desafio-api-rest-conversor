package br.com.acert.api.conversor.temperaturas.service.reports;

import br.com.acert.api.conversor.temperaturas.model.dtos.EscalaTermometricaWrapper;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ServiceException;

import java.time.LocalDate;

public interface IEscalaTermometricaReportService {

    EscalaTermometricaWrapper listarPorId(Long id);

    EscalaTermometricaWrapper listarTodos();

    EscalaTermometricaWrapper listarAtivos();

    EscalaTermometricaWrapper listarInativos();

    EscalaTermometricaWrapper listarPorDescricao(String descricao) throws ServiceException;

    EscalaTermometricaWrapper listarPorDataInsercao(LocalDate data) throws ServiceException;
}
