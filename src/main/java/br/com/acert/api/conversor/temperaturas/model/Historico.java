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
@JacksonXmlRootElement(localName = "Historicos")
@Entity
@Table(name = "fd02_historicos", schema = "conversor_service")
@Inheritance(strategy = InheritanceType.JOINED)
public class Historico extends AbstractEntity {

    @Tolerate
    public Historico() {
        super();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escala_origem")
    EscalaTermometrica escalaTermometricaOrigem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_escala_destino")
    EscalaTermometrica escalaTermometricaDestino;

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 19, fraction = 6)
    @JacksonXmlProperty
    @Column(name = "valor_origem")
    BigDecimal valorOrigem = BigDecimal.ZERO;

    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 19, fraction = 6)
    @JacksonXmlProperty
    @Column(name = "valor_resultado")
    BigDecimal resultado = BigDecimal.ZERO;
}
