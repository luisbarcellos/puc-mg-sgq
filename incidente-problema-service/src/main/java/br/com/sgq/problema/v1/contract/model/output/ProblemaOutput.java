package br.com.sgq.problema.v1.contract.model.output;

import br.com.sgq.incidente.v1.contract.model.IncidenteOutput;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProblemaOutput {
    private Long idProblema;
    private String tipo;
    private String gravidade;
    private String descricao;
    private LocalDate dataInclusao;
    private List<IncidenteOutput> incidentes;
}