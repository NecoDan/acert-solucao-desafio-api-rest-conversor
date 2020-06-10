package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;

public interface IHistoricoService {

    Historico salvar(Historico historico) throws ValidadorException;

}
