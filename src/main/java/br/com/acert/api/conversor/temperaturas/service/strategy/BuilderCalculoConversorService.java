package br.com.acert.api.conversor.temperaturas.service.strategy;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.service.negocio.TipoCalculoConversao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuilderCalculoConversorService implements IBuilderCalculoConversorService {

    private final BuilderCalculoConversorStrategy builderCalculoConversorStrategy;

    @Override
    public TipoCalculoConversao obterTipoCalculoConversorAPartir(Historico historico) {
        return builderCalculoConversorStrategy.obter(historico);
    }
}
