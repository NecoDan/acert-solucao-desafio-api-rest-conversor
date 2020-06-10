package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;

import java.util.Optional;

public interface IHistoricoService {

    Optional<Historico> recuperarPorId(Long id);

    Historico salvar(Historico historico) throws ValidadorException;

}
