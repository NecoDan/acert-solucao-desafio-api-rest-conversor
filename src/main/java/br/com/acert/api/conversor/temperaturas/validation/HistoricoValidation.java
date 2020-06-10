package br.com.acert.api.conversor.temperaturas.validation;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class HistoricoValidation implements IHistoricoValidation {

    @Override
    public void validar(Historico historico) throws ValidadorException {
        validarSomente(historico);

        if (Objects.isNull(historico.getEscalaTermometricaDestino()) || Objects.isNull(historico.getEscalaTermometricaDestino().getId()))
            throw new ValidadorException("Escala de destino a ser convertida, encontra-se inválido e/ou inexistente [NULO].");

        if (Objects.isNull(historico.getEscalaTermometricaOrigem()) || Objects.isNull(historico.getEscalaTermometricaOrigem().getId()))
            throw new ValidadorException("Escala de origem para efetuar a conversão, encontra-se inválido e/ou inexistente [NULO].");

        if (Objects.isNull(historico.getValorGrauOrigem()))
            throw new ValidadorException("O valor de origem para a efetuar a conversão, encontra-se inválidao e/ou inexistento [NULA]");

        if (historico.getValorGrauOrigem().compareTo(BigDecimal.ZERO) == 0)
            throw new ValidadorException("O valor de origem para a efetuar a conversão, encontra-se com valor igual à zero (0).");
    }

    @Override
    public void validarSomente(Historico historico) throws ValidadorException {
        if (Objects.isNull(historico))
            throw new ValidadorException("Nenhum historico encontrado. Historico, encontra-se inválido e/ou inexistente [NULO].");
    }
}
