package br.com.acert.api.conversor.temperaturas.service.strategy;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.service.negocio.TipoCalculoConversao;

public interface IFactoryCalculoConversorService {

    boolean isAppliable(Historico historico);

    TipoCalculoConversao obterCalculoConversao(Historico historico);
}
