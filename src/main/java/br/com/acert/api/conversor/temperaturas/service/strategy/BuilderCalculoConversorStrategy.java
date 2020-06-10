package br.com.acert.api.conversor.temperaturas.service.strategy;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.service.negocio.TipoCalculoConversao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BuilderCalculoConversorStrategy {

    private final List<IFactoryCalculoConversorService> factoryCalculoConversorServices;

    public BuilderCalculoConversorStrategy(List<IFactoryCalculoConversorService> factoryCalculoConversorServiceList) {
        this.factoryCalculoConversorServices = factoryCalculoConversorServiceList;
    }

    public TipoCalculoConversao obter(Historico historico) {
        Optional<TipoCalculoConversao> optionalTipoCalculoConversao = this.factoryCalculoConversorServices.stream()
                .filter(Objects::nonNull)
                .filter(factoryCalculoConversorService -> factoryCalculoConversorService.isAppliable(historico))
                .map(factoryCalculoConversorService -> factoryCalculoConversorService.obterCalculoConversao(historico))
                .findFirst();
        return optionalTipoCalculoConversao.orElse(TipoCalculoConversao.SEM_CALCULO);
    }
}
