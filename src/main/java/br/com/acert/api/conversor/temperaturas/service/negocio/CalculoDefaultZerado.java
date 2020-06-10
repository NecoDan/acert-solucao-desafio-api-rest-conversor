package br.com.acert.api.conversor.temperaturas.service.negocio;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculoDefaultZerado implements IRegraCalculoConverteEscalaTermoService {

    @Override
    public BigDecimal converte(Historico historico) {
        return BigDecimal.ZERO;
    }

    @Override
    public Double converter(Historico historico) {
        return converte(historico).doubleValue();
    }
}
