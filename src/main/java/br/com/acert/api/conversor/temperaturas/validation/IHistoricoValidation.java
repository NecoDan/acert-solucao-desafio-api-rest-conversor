package br.com.acert.api.conversor.temperaturas.validation;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;

public interface IHistoricoValidation {

    void validar(Historico historico) throws ValidadorException;

    void validarSomente(Historico historico) throws ValidadorException;

}
