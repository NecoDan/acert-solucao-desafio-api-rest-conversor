package br.com.acert.api.conversor.temperaturas.repository;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {


}
