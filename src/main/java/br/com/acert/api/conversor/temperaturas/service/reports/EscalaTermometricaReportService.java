package br.com.acert.api.conversor.temperaturas.service.reports;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.dtos.EscalaTermometricaWrapper;
import br.com.acert.api.conversor.temperaturas.repository.EscalaTermometricaRepository;
import br.com.acert.api.conversor.temperaturas.util.exceptions.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EscalaTermometricaReportService implements IEscalaTermometricaReportService {

    private final EscalaTermometricaRepository escalaTermometricaRepository;

    @Override
    public EscalaTermometricaWrapper listarPorId(Long id) {
        Optional<EscalaTermometrica> escalaTermometricaOptional = this.escalaTermometricaRepository.findById(id);
        return escalaTermometricaOptional.map(escalaTermometrica -> getWrapper(Collections.singletonList(escalaTermometrica))).orElse(getWrapper(Collections.emptyList()));
    }

    @Override
    public EscalaTermometricaWrapper listarTodos() {
        return getWrapper(this.escalaTermometricaRepository.findAll());
    }

    @Override
    public EscalaTermometricaWrapper listarAtivos() {
        return getWrapper(this.escalaTermometricaRepository.findAllByAtivo(true));
    }

    @Override
    public EscalaTermometricaWrapper listarInativos() {
        return getWrapper(this.escalaTermometricaRepository.findAllByAtivo(false));
    }

    @Override
    public EscalaTermometricaWrapper listarPorDescricao(String descricao) throws ServiceException {
        return getWrapper(this.escalaTermometricaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @Override
    public EscalaTermometricaWrapper listarPorDataInsercao(LocalDate data) throws ServiceException {
        return getWrapper(this.escalaTermometricaRepository.recuperarTodosPorDataInsercao(data));
    }

    private EscalaTermometricaWrapper getWrapper(List<EscalaTermometrica> escalaTermometricaList) {
        return EscalaTermometricaWrapper.builder().escalaTermometricas(escalaTermometricaList).build();
    }
}
