package br.com.sgq.norma.v1.contract.model;

import br.com.sgq.naoconformidade.v1.contract.model.NaoConformidadeOutput;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NormaOutput {
    private Long idNorma;
    private String tipo;
    private String descricao;
    private String impactoErgonomia;
    private LocalDate dataInclusao;
    private NaoConformidadeOutput naoConformidade;
}