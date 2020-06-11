package br.com.acert.api.conversor.temperaturas.model;

import br.com.acert.api.conversor.temperaturas.service.negocio.TipoCalculoConversao;
import br.com.acert.api.conversor.temperaturas.util.domain.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.experimental.Tolerate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Objects;

@ToString
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = false)
@JacksonXmlRootElement(localName = "Historicos")
@Entity
@Table(name = "fd02_historico", schema = "conversor_service")
@Inheritance(strategy = InheritanceType.JOINED)
public class Historico extends AbstractEntity {

    @Tolerate
    public Historico() {
        super();
    }

    @JacksonXmlProperty
    @Fetch(FetchMode.SELECT)
    @ManyToOne
    @JoinColumn(name = "id_escala_origem")

    EscalaTermometrica escalaTermometricaOrigem;

    @JacksonXmlProperty
    @Fetch(FetchMode.SELECT)
    @ManyToOne
    @JoinColumn(name = "id_escala_destino")
    EscalaTermometrica escalaTermometricaDestino;

    @JacksonXmlProperty
    @DecimalMin(value = "0.0", inclusive = true)
    @Digits(integer = 19, fraction = 6)
    @Column(name = "valor_grau_origem")
    BigDecimal valorGrauOrigem;

    @JacksonXmlProperty
    @Digits(integer = 19, fraction = 6)
    @Column(name = "valor_grau_resultado")
    @Getter
    @Setter(AccessLevel.PRIVATE)
    BigDecimal valorGrauResultado;

    @Transient
    @JsonIgnore
    private TipoCalculoConversao tipoCalculoConversao;

    public void calcularResultado() {
        if (Objects.isNull(this.tipoCalculoConversao)) {
            this.valorGrauResultado = BigDecimal.ZERO;
            return;
        }
        this.valorGrauResultado = this.tipoCalculoConversao.getRegraCalculoConversorService().converte(this);
        this.valorGrauResultado = this.valorGrauResultado.setScale(2, BigDecimal.ROUND_UP);
    }
}
