package br.com.sgq.produto.v1.impl.model;

import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PRODUTO", schema = "public")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long idProduto;

    @NotNull
    @Column(name = "NOME")
    private String nome;

    @NotNull
    @Column(name = "TIPO")
    private String tipo;

    @NotNull
    @Column(name = "DESCRICAO")
    private String descricao;

    @ToString.Exclude
    @ManyToMany(mappedBy = "produtoModelList")
    private List<IncidenteModel> incidenteProblemaModelList;
}