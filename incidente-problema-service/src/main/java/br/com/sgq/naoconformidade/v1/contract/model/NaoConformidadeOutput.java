package br.com.sgq.naoconformidade.v1.contract.model;

import br.com.sgq.problema.v1.contract.model.output.ProblemaOutput;
import br.com.sgq.riscoacidente.v1.contract.model.RiscoAcidenteOutput;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NaoConformidadeOutput {
    private Long idNaoConformidade;
    private String tipo;
    private String descricao;
    private RiscoAcidenteOutput riscoAcidente;
    private ProblemaOutput problema;
}