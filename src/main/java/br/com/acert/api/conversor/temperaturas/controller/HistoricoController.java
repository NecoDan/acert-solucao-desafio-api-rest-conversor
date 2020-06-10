package br.com.acert.api.conversor.temperaturas.controller;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import br.com.acert.api.conversor.temperaturas.model.dtos.HistoricoWrapper;
import br.com.acert.api.conversor.temperaturas.service.IGeradorHistoricosService;
import br.com.acert.api.conversor.temperaturas.service.reports.IHistoricoReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@RestController
@Validated
@RequestMapping(path = "historicos")
@RequiredArgsConstructor
@Api(value = "Historico")
public class HistoricoController {

    private final IHistoricoReportService historicoReportService;
    private final IGeradorHistoricosService geradorHistoricoService;

    @ApiOperation(value = "Retorna todos os históricos de conversão existentes.")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAll() {
        return retornarListaHistoricosConversao();
    }

    @ApiOperation(value = "Retornar todos os históricos de conversão, em formato XML apenas.")
    @GetMapping(path = "/exibeTodosEmXML", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllInXML() {
        return retornarListaHistoricosConversao();
    }

    @ApiOperation(value = "Retornar todos os históricos de conversão, em formato JSON apenas.")
    @GetMapping(path = "/exibeTodosEmJSON", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> listAllInJson() {
        return retornarListaHistoricosConversao();
    }

    @ApiOperation(value = "Retorna um único histórico de conversão existente, a partir de seu [ID] (número único) registrado...")
    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> buscaPorId(@PathVariable Long id) {
        try {
            return getResponseDefault(this.historicoReportService.listarPorId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Responsável por persistir somente um único Histórico de conversão, a partir de um filtro consumer como corpo da requisição...")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> save(@Valid @RequestBody Historico historico) {
        try {
            return new ResponseEntity<>(this.geradorHistoricoService.gerar(Collections.singletonList(historico)), HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar historico(s): " + ex.getMessage());
        }
    }

    @ApiOperation(value = "Responsável por persistir múltiplos Históricos, a partir de um filtro consumer como corpo da requisição...")
    @PostMapping(path = "/salvaVarios", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> saveAll(@Valid @RequestBody List<Historico> historicoList) {
        try {
            return new ResponseEntity<>(this.geradorHistoricoService.gerar(historicoList), HttpStatus.CREATED);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar historico(s): " + ex.getMessage());
        }
    }

    @ApiOperation(value = "Retorna todos os históricos de conversão, a partir do filtro <b>valor</b> referente ao valor resultado convertido do valor origem, que estejam igual ou maior ao passado como parâmetro...")
    @GetMapping(path = "/buscaValorResultadoIgualOuMaior", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorValorResultadoMaiorOuIgual(@RequestParam("valor") BigDecimal valor) {
        try {
            return getResponseDefault(this.historicoReportService.listarPorResultadoMaiorOuIgualQue(valor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retorna todos os históricos de conversão, a partir do filtro <b>valor</b> referente ao valor resultado convertido do valor origem, que estejam igual ou menor ao passado como parâmetro...")
    @GetMapping(path = "/buscaValorResultadoIgualOuMenor", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorValorResultadoMenorOuIgual(@RequestParam("valor") BigDecimal valor) {
        try {
            return getResponseDefault(this.historicoReportService.listarPorResultadoMenorOuIgualQue(valor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retorna todos os históricos de conversão, a partir do filtro <b>valor</b> referente ao valor origem a ser convertido, que estejam igual ou maior ao passado como parâmetro...")
    @GetMapping(path = "/buscaValorOrigemIgualOuMaior", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorValorOrigemMaiorOuIgual(@RequestParam("valor") BigDecimal valor) {
        try {
            return getResponseDefault(this.historicoReportService.listarPorValorOrigemMaiorOuIgualQue(valor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retorna todos os históricos de conversão, a partir do filtro <b>valor</b> referente ao valor origem a ser convertido, que estejam igual ou menor ao passado como parâmetro...")
    @GetMapping(path = "/buscaValorOrigemIgualOuMenor", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorValorOrigemMenorOuIgual(@RequestParam("valor") BigDecimal valor) {
        try {
            return getResponseDefault(this.historicoReportService.listarPorValorOrigemMenorOuIgualQue(valor));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retorna todos os históricos de conversão existentes, a partir do filtro <b>data</b> aos quais foram gravadas no banco de dados. Com o formato da data (dd/MM/yyyy).")
    @GetMapping(path = "/buscaPorDataCadastro", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorDataCadastro(@RequestParam("data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {
        try {
            return getResponseDefault(this.historicoReportService.listarPorDataInsercao(data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retorna todos os históricos de conversão existentes, a partir dos filtro(s) <b>Data Inicial</b> e <b>Data Final</b> aos quais foram gravadas no banco de dados. Com o formato da data (dd/MM/yyyy).")
    @GetMapping(path = "/buscaPorPeriodo", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorPeriodo(@RequestParam("dataInicio") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataInicio,
                                               @RequestParam("dataFim") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate dataFim) {
        try {
            return getResponseDefault(this.historicoReportService.listarPorPeriodo(dataInicio, dataFim));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    private ResponseEntity<?> getResponseDefault(HistoricoWrapper historicoWrapper) {
        if (Objects.isNull(historicoWrapper) || historicoWrapper.isParamsInValidos())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(historicoWrapper);
    }

    private ResponseEntity<?> retornarListaHistoricosConversao() {
        try {
            return getResponseDefault(this.historicoReportService.listarTodos());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao executar a busca de dados: " + ex.getMessage());
        }
    }
}
