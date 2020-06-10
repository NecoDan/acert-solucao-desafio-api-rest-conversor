package br.com.acert.api.conversor.temperaturas.model.dtos;

import br.com.acert.api.conversor.temperaturas.model.Historico;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@ToString
@JacksonXmlRootElement(localName = "HistoricoConversoes")
@AllArgsConstructor
public class HistoricoWrapper {

    @JacksonXmlProperty(localName = "Historico")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Historico> historicos;

    public void add(Historico historico) {
        if (Objects.isNull(this.historicos))
            this.historicos = new ArrayList<>();
        this.historicos.add(historico);
    }

    @JsonIgnore
    public HistoricoWrapper adicionar(Historico historico) {
        this.add(historico);
        return this;
    }

    @JsonIgnore
    public boolean isParamsInValidos() {
        return (this.getHistoricos().isEmpty()) || this.getHistoricos().size() == 1 && Objects.isNull(this.getHistoricos().get(0));
    }
}
