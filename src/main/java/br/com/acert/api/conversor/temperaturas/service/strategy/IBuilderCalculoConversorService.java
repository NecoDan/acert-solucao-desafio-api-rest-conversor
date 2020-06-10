package br.com.acert.api.conversor.temperaturas.service.strategy;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.service.negocio.TipoCalculoConversao;

public interface IBuilderCalculoConversorService {
    TipoCalculoConversao obterTipoCalculoConversorAPartir(Historico historico);
}
