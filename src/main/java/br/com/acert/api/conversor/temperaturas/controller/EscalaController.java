package br.com.acert.api.conversor.temperaturas.controller;

import br.com.acert.api.conversor.temperaturas.model.dtos.EscalaTermometricaWrapper;
import br.com.acert.api.conversor.temperaturas.service.reports.EscalaTermometricaReportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Objects;

@RestController
@Validated
@RequestMapping(path = "escala")
@RequiredArgsConstructor
@Api(value = "EscalaTermometrica")
public class EscalaController {

    private final EscalaTermometricaReportService escalaTermometricaReportService;

    @ApiOperation(value = "Retornar todas as escalas termométricas existentes...")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAll() {
        return retornarListaEscalasTermometricas();
    }

    @ApiOperation(value = "Retornar todas as escalas termométricas, em formato XML...")
    @GetMapping(path = "/exibeTodosEmXML", produces = {MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllInXML() {
        return retornarListaEscalasTermometricas();
    }

    @ApiOperation(value = "Retornar todas as escalas termométricas, em formato JSON...")
    @GetMapping(path = "/exibeTodosEmJSON", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> listAllInJson() {
        return retornarListaEscalasTermometricas();
    }

    @ApiOperation(value = "Retorna uma única escala termométrica existente, a partir de seu [ID] (número único e sequêncial) registrado...")
    @GetMapping(path = "/{escalaId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> buscaPorId(@PathVariable("escalaId") Long escalaId) {
        try {
            return getResponseDefault(escalaTermometricaReportService.listarPorId(escalaId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retornar todas as escalas termométricas existentes e encontram-se ativos...")
    @GetMapping(path = "/exibeAtivos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllAtivos() {
        try {
            return getResponseDefault(this.escalaTermometricaReportService.listarAtivos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retornar todas as escalas termométricas existentes e encontram-se inativadas...")
    @GetMapping(path = "/exibeInativos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllInAtivos() {
        try {
            return getResponseDefault(this.escalaTermometricaReportService.listarInativos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retorna todas as escalas termometricas existentes, a partir do filtro <b>descricao</b> que estejam igual ou semelhante a passada como parâmetro ...")
    @GetMapping(path = "/buscaPorDescricao", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorDescricao(@RequestParam("descricao") String descricao) {
        try {
            return getResponseDefault(this.escalaTermometricaReportService.listarPorDescricao(descricao));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    @ApiOperation(value = "Retorna todos os pedidos de existentes, a partir do filtro <b>data</b> aos quais foram gravadas no banco de dados. Com o formato da data (dd/MM/yyyy).")
    @GetMapping(path = "/buscaPorDataCadastro", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> listAllPorDataCadastro(@RequestParam("data") @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate data) {
        try {
            return getResponseDefault(escalaTermometricaReportService.listarPorDataInsercao(data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).header("error", e.getMessage()).build();
        }
    }

    private ResponseEntity<?> getResponseDefault(EscalaTermometricaWrapper escalaTermometricaWrapper) {
        if (Objects.isNull(escalaTermometricaWrapper) || escalaTermometricaWrapper.isParamsInValidos())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(escalaTermometricaWrapper);
    }

    private ResponseEntity<?> retornarListaEscalasTermometricas() {
        try {
            return getResponseDefault(escalaTermometricaReportService.listarTodos());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao executar a busca de dados: " + ex.getMessage());
        }
    }
}
