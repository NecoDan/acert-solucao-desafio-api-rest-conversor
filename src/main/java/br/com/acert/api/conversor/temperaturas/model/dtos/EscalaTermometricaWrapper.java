package br.com.acert.api.conversor.temperaturas.model.dtos;

import br.com.acert.api.conversor.temperaturas.model.EscalaTermometrica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "EscalaTermometrica")
@AllArgsConstructor
public class EscalaTermometricaWrapper implements Serializable {

    private static final long serialVersionUID = 22L;

    @JacksonXmlProperty(localName = "EscalaTermometrica")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<EscalaTermometrica> escalaTermometricas = new ArrayList<>();

    public void add(EscalaTermometrica escalaTermometrica) {
        this.escalaTermometricas.add(escalaTermometrica);
    }

    @JsonIgnore
    public EscalaTermometricaWrapper adicionar(EscalaTermometrica escalaTermometrica) {
        this.add(escalaTermometrica);
        return this;
    }

    @JsonIgnore
    public boolean isParamsInValidos() {
        return (this.getEscalaTermometricas().isEmpty()) || this.getEscalaTermometricas().size() == 1 && Objects.isNull(this.getEscalaTermometricas().get(0));
    }
}
