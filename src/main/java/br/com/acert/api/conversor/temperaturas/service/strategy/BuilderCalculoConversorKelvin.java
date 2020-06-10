package br.com.acert.api.conversor.temperaturas.service.strategy;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.service.negocio.TipoCalculoConversao;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BuilderCalculoConversorKelvin implements IFactoryCalculoConversorService {

    @Override
    public boolean isAppliable(Historico historico) {
        return (Objects.nonNull(historico) && Objects.nonNull(historico.getEscalaTermometricaDestino()) && historico.getEscalaTermometricaDestino().isKelvin());
    }

    @Override
    public TipoCalculoConversao obterCalculoConversao(Historico historico) {
        return TipoCalculoConversao.KELVIN;
    }
}
