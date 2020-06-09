package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.repository.HistoricoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoricoService implements IHistoricoService {

    private final HistoricoRepository historicoRepository;

}
