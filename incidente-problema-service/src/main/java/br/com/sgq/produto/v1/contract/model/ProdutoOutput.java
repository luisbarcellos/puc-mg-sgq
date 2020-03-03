package br.com.sgq.produto.v1.contract.model;

import br.com.sgq.incidente.v1.contract.model.IncidenteOutput;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoOutput {
    private Long idProduto;
    private String nome;
    private String tipo;
    private String descricao;
    private List<IncidenteOutput> incidentes;
}