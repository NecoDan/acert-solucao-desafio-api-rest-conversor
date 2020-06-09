package br.com.acert.api.conversor.temperaturas.repository;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface EscalaTermometricaRepository extends JpaRepository<EscalaTermometrica, Long> {

    List<EscalaTermometrica> findAllByAtivo(@Param("ativo") boolean ativo);

    List<EscalaTermometrica> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

    @Query(value = "select * from conversor_service.fd01_escala_termometrica where date(data) = :#{#data} order by descricao", nativeQuery = true)
    List<EscalaTermometrica> recuperarTodosPorDataInsercao(@Param("data") LocalDate data);
}
