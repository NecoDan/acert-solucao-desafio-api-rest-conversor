package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.repository.EscalaTermometricaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EscalaTermometricaService implements IEscalaTermometricaService {

    private final EscalaTermometricaRepository escalaTermometricaRepository;

    @Override
    public Optional<EscalaTermometrica> recuperarPorId(Long id) {
        return this.escalaTermometricaRepository.findById(id);
    }
}
