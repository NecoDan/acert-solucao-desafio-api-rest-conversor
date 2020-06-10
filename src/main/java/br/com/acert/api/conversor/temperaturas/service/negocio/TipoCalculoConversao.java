package br.com.acert.api.conversor.temperaturas.service.negocio;

public enum TipoCalculoConversao {

    SEM_CALCULO(new CalculoDefaultZerado()),

    CELSIUS(new CalculoConverteEmCelsius(new CalculadoraConversorEscalas())),

    FAHRENHEIT(new CalculoConverteEmFahrenheit(new CalculadoraConversorEscalas())),

    KELVIN(new CalculoConverteEmKelvin(new CalculadoraConversorEscalas()));

    private final IRegraCalculoConverteEscalaTermoService regraCalculoConversorService;

    TipoCalculoConversao(IRegraCalculoConverteEscalaTermoService regraCalculoConversorService) {
        this.regraCalculoConversorService = regraCalculoConversorService;
    }

    public IRegraCalculoConverteEscalaTermoService getRegraCalculoConversorService() {
        return this.regraCalculoConversorService;
    }
}
