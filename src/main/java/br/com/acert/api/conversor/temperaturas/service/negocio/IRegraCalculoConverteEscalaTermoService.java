package br.com.acert.api.conversor.temperaturas.service.negocio;

import br.com.acert.api.conversor.temperaturas.model.Historico;

import java.math.BigDecimal;

public interface IRegraCalculoConverteEscalaTermoService {

    BigDecimal converte(Historico historico);

    Double converter(Historico historico);
}
