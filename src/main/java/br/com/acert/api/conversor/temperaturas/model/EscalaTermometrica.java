package br.com.acert.api.conversor.temperaturas.model;

import br.com.acert.api.conversor.temperaturas.util.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Objects;

@ToString
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@JacksonXmlRootElement(localName = "EscalaTermometrica")
@Entity
@Table(name = "fd01_escala_termometrica", schema = "conversor_service")
@Inheritance(strategy = InheritanceType.JOINED)
public class EscalaTermometrica extends AbstractEntity {

    private static final long CELSIUS = 1;
    private static final long FAHRENHEIT = 2;
    private static final long KELVIN = 3;

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

    @JsonIgnore
    private boolean isIdentificadorValido() {
        return (Objects.nonNull(this.getId()));
    }

    @JsonIgnore
    public boolean isCelsius() {
        return (isIdentificadorValido() && Objects.equals(getId(), CELSIUS));
    }

    @JsonIgnore
    public boolean isFahrenheit() {
        return (isIdentificadorValido() && Objects.equals(getId(), FAHRENHEIT));
    }

    @JsonIgnore
    public boolean isKelvin() {
        return (isIdentificadorValido() && Objects.equals(getId(), KELVIN));
    }
}
