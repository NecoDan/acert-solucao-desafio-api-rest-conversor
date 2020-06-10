package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;

import java.util.List;

public interface IGeradorHistoricosService {

    HistoricoWrapper gerar(List<Historico> historicos) throws ValidadorException;

    Historico gerar(Historico historico) throws ValidadorException;
}
