package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;

import java.util.Optional;

public interface IEscalaTermometricaService {
    Optional<EscalaTermometrica> recuperarPorId(Long id);
}
