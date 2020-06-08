package br.com.acert.api.conversor.temperaturas.util.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class ResourceNotFoundDetails {
    private String titulo;
    private int status;
    private String detalhes;
    private Long timestamp;
    private String mensagem;
}
