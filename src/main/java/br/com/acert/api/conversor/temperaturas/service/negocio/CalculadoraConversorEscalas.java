package br.com.acert.api.conversor.temperaturas.service.negocio;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Builder
public class CalculadoraConversorEscalas implements ICalculadoraConversorEscalas {

    private static final double VALOR_PADRAO_CALCULO_ESCALA_FAHRENHEIT = 1.8;
    private static final double VALOR_PADRAO_CALCULO_ESCALA_KELVIN_PRA_FAHRENHEIT = 459.67;

    @Override
    public BigDecimal converterAPartirHistorico(Historico historico) {
        return Stream.of(historico.getEscalaTermometricaOrigem())
                .filter(Objects::nonNull)
                .map(e -> converterAPartirEscalas(historico, e))
                .collect(Collectors.toList())
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public boolean isParamInvalidos(Historico historico) {
        return (Objects.isNull(historico) || isEscalasTermometricasInvalidas(historico));
    }

    private BigDecimal converterAPartirEscalas(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return Stream.of(converterFahrenheitParaCelsius(historico, escalaTermometricaOrigem)
                , converterCelsiusParaFahrenheit(historico, escalaTermometricaOrigem)
                , converterCelsiusParaKelvin(historico, escalaTermometricaOrigem)
                , converterKelvinParaCelsius(historico, escalaTermometricaOrigem)
                , converterKelvinParaFahrenheit(historico, escalaTermometricaOrigem)
                , converterFahrenheitParaKelvin(historico, escalaTermometricaOrigem))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal converterCelsiusParaFahrenheit(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return (escalaTermometricaOrigem.isCelsius() && historico.getEscalaTermometricaDestino().isFahrenheit())
                ? calcularCelsiusParaFahrenheit(historico)
                : BigDecimal.ZERO;
    }

    private BigDecimal converterFahrenheitParaCelsius(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return (escalaTermometricaOrigem.isFahrenheit() && historico.getEscalaTermometricaDestino().isCelsius())
                ? calcularFahrenheitParaCelsius(historico, escalaTermometricaOrigem)
                : BigDecimal.ZERO;
    }

    private BigDecimal converterCelsiusParaKelvin(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return (escalaTermometricaOrigem.isCelsius() && historico.getEscalaTermometricaDestino().isKelvin())
                ? calcularCelsiusParaKelvin(historico)
                : BigDecimal.ZERO;
    }

    private BigDecimal converterKelvinParaCelsius(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return (escalaTermometricaOrigem.isKelvin() && historico.getEscalaTermometricaDestino().isCelsius())
                ? calcularKelvinParaCelsius(historico, escalaTermometricaOrigem)
                : BigDecimal.ZERO;
    }

    private BigDecimal converterKelvinParaFahrenheit(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return (escalaTermometricaOrigem.isKelvin() && historico.getEscalaTermometricaDestino().isFahrenheit())
                ? calcularKelvinParaFahrenheit(historico)
                : BigDecimal.ZERO;
    }

    private BigDecimal converterFahrenheitParaKelvin(Historico historico, EscalaTermometrica escalaTermometrica) {
        return (escalaTermometrica.isFahrenheit() && historico.getEscalaTermometricaDestino().isKelvin())
                ? calcularFahrenheitParaKelvin(historico)
                : BigDecimal.ZERO;
    }

    private BigDecimal calcularCelsiusParaFahrenheit(Historico historico) {
        return BigDecimal.valueOf(VALOR_PADRAO_CALCULO_ESCALA_FAHRENHEIT)
                .multiply(historico.getValorGrauOrigem())
                .add(historico.getEscalaTermometricaDestino().getPontoFusao());
    }

    private BigDecimal calcularFahrenheitParaCelsius(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return historico.getValorGrauOrigem()
                .subtract(escalaTermometricaOrigem.getPontoFusao())
                .divide(BigDecimal.valueOf(VALOR_PADRAO_CALCULO_ESCALA_FAHRENHEIT), 3, RoundingMode.CEILING);
    }

    private BigDecimal calcularKelvinParaFahrenheit(Historico historico) {
        return historico.getValorGrauOrigem()
                .multiply(BigDecimal.valueOf(VALOR_PADRAO_CALCULO_ESCALA_FAHRENHEIT))
                .subtract(BigDecimal.valueOf(VALOR_PADRAO_CALCULO_ESCALA_KELVIN_PRA_FAHRENHEIT));
    }

    private BigDecimal calcularFahrenheitParaKelvin(Historico historico) {
        return historico.getValorGrauOrigem()
                .add(BigDecimal.valueOf(VALOR_PADRAO_CALCULO_ESCALA_KELVIN_PRA_FAHRENHEIT))
                .divide(BigDecimal.valueOf(VALOR_PADRAO_CALCULO_ESCALA_FAHRENHEIT), 3, RoundingMode.CEILING);
    }

    private BigDecimal calcularCelsiusParaKelvin(Historico historico) {
        return historico.getValorGrauOrigem().add(historico.getEscalaTermometricaDestino().getPontoFusao());
    }

    private BigDecimal calcularKelvinParaCelsius(Historico historico, EscalaTermometrica escalaTermometricaOrigem) {
        return historico.getValorGrauOrigem().subtract(escalaTermometricaOrigem.getPontoFusao());
    }

    private boolean isEscalasTermometricasInvalidas(Historico historico) {
        return (Objects.isNull(historico.getEscalaTermometricaDestino())
                || Objects.isNull(historico.getEscalaTermometricaOrigem())
                || Objects.isNull(historico.getValorGrauOrigem())
                || historico.getValorGrauOrigem().compareTo(BigDecimal.ZERO) == 0
                || Objects.isNull(historico.getEscalaTermometricaOrigem().getPontoFusao())
                || Objects.isNull(historico.getEscalaTermometricaDestino().getPontoFusao()));
    }
}
