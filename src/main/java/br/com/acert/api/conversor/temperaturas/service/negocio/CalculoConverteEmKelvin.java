package br.com.acert.api.conversor.temperaturas.service.negocio;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CalculoConverteEmKelvin implements IRegraCalculoConverteEscalaTermoService {

    private final ICalculadoraConversorEscalas calculadoraConversorEscalas;

    @Override
    public BigDecimal converte(Historico historico) {
        return (calculadoraConversorEscalas.isParamInvalidos(historico)) ? BigDecimal.ZERO : calculadoraConversorEscalas.converterAPartirHistorico(historico);
    }

    @Override
    public Double converter(Historico historico) {
        return converte(historico).doubleValue();
    }
}
