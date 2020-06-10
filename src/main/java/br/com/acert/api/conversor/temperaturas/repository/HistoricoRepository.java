package br.com.acert.api.conversor.temperaturas.repository;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import br.com.acert.api.conversor.temperaturas.model.Historico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {

    @Query(value = "select * from conversor_service.fd02_historico as historico inner join conversor_service.fd01_escala_termometrica as escala_origem on (escala_origem.id = historico.id_escala_origem) where historico.id_escala_origem = :#{#escalaTermometrica.id} order by data", nativeQuery = true)
    List<Historico> recuperarTodosPorEscalaTermometricaOrigem(@Param("escalaTermometrica") EscalaTermometrica escalaTermometrica);

    @Query(value = "select * from conversor_service.fd02_historico as historico inner join conversor_service.fd01_escala_termometrica as escala_destino on (escala_destino.id = historico.id_escala_destino) where historico.id_escala_destino = :#{#escalaTermometrica.id} order by data", nativeQuery = true)
    List<Historico> recuperarTodosPorEscalaTermometricaDestino(@Param("escalaTermometrica") EscalaTermometrica escalaTermometrica);

    @Query(value = "select * from conversor_service.fd02_historico where date(data) = :#{#data} order by id", nativeQuery = true)
    List<Historico> recuperarTodosPorDataInsercao(@Param("data") LocalDate data);

    @Query(value = "select * from conversor_service.fd02_historico where date(data) between :#{#dataInicio} and :#{#dataFim} order by id", nativeQuery = true)
    List<Historico> recuperarTodosPorPeriodo(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    List<Historico> findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoLessThanEqual(@Param("valorGrauResultado") BigDecimal valorGrauResultado);

    List<Historico> findAllByValorGrauResultadoIsNotNullAndValorGrauResultadoGreaterThanEqual(@Param("valorGrauResultado") BigDecimal valorGrauResultado);

    List<Historico> findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemLessThanEqual(@Param("valorGrauOrigem") BigDecimal valorGrauOrigem);

    List<Historico> findAllByValorGrauOrigemIsNotNullAndValorGrauOrigemGreaterThanEqual(@Param("valorGrauOrigem") BigDecimal valorGrauOrigem);

}
