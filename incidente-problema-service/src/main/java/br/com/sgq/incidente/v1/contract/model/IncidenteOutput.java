package br.com.sgq.incidente.v1.contract.model;

import br.com.sgq.produto.v1.contract.model.ProdutoOutput;
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
public class IncidenteOutput {
    private Long idIncidente;
    private String tipo;
    private String gravidade;
    private String descricao;
    private LocalDate dataInclusao;
    private List<ProdutoOutput> produtos;
}