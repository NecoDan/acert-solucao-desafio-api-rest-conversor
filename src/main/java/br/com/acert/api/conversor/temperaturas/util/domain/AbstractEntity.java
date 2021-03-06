package br.com.acert.api.conversor.temperaturas.util.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
@SuperBuilder
@Data
@EqualsAndHashCode(of = "id")
@JacksonXmlRootElement
public class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(value = AccessLevel.PUBLIC)
    @JacksonXmlProperty
    private Long id;

    @JacksonXmlProperty
    @Column(name = "data")
    @Setter(value = AccessLevel.PUBLIC)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", locale = "pt-BR", timezone = "UTC")
    private LocalDateTime data;

    @JacksonXmlProperty
    @Setter(value = AccessLevel.PUBLIC)
    @Column(name = "ativo", columnDefinition = "tinyint(1) default 1", nullable = false)
    private boolean ativo;

    @Tolerate
    public AbstractEntity() {
    }

    public void gerarDataCorrente() {
        if (Objects.isNull(this.getData()))
            this.setData(LocalDateTime.now());
    }

    public void ativado() {
        this.ativo = true;
    }

    public void desativado() {
        this.ativo = false;
    }
}
