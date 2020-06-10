package br.com.acert.api.conversor.temperaturas.service;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.repository.HistoricoRepository;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ValidadorException;
import br.com.acert.api.conversor.temperaturas.validation.IHistoricoValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class HistoricoService implements IHistoricoService {

    private final HistoricoRepository historicoRepository;
    private final IHistoricoValidation historicoValidation;

    @Override
    public Optional<Historico> recuperarPorId(Long id) {
        return this.historicoRepository.findById(id);
    }

    @Override
    @Transactional
    public Historico salvar(Historico historico) throws ValidadorException {
        this.historicoValidation.validarSomente(historico);
        return this.historicoRepository.save(historico);
    }
}
