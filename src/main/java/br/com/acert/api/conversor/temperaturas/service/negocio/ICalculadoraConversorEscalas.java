package br.com.acert.api.conversor.temperaturas.service.negocio;

import br.com.acert.api.conversor.temperaturas.model.Historico;

import java.math.BigDecimal;

public interface ICalculadoraConversorEscalas {

    BigDecimal converterAPartirHistorico(Historico historico);

    boolean isParamInvalidos(Historico historico);
}
