package br.com.acert.api.conversor.temperaturas.model;

import br.com.acert.api.conversor.temperaturas.util.domain.AbstractEntity;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@ToString
@SuperBuilder
@Data
@JacksonXmlRootElement(localName = "EscalaTermometrica")
@Entity
@Table(name = "fd01_escala_termometrica", schema = "conversor_service")
@Inheritance(strategy = InheritanceType.JOINED)
public class EscalaTermometrica extends AbstractEntity {

    @Tolerate
    public EscalaTermometrica() {
        super();
    }

    @JacksonXmlProperty
    @Column(name = "descricao")
    private String descricao;

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 19, fraction = 6)
    @JacksonXmlProperty
    @Column(name = "ponto_fusao")
    private BigDecimal pontoFusao;

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 19, fraction = 6)
    @JacksonXmlProperty
    @Column(name = "pontoEbulicao")
    private BigDecimal pontoEbulicao;
}
