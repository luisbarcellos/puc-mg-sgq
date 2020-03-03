package br.com.sgq.incidente.v1.impl.model;

import br.com.sgq.problema.v1.impl.model.ProblemaModel;
import br.com.sgq.produto.v1.impl.model.ProdutoModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "INCIDENTE", schema = "public")
public class IncidenteModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_INCIDENTE")
    private Long idIncidente;

    @NotNull
    @Column(name = "TIPO")
    private String tipo;

    @NotNull
    @Column(name = "GRAVIDADE")
    private String gravidade;

    @NotNull
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "DATA_INCLUSAO")
    private LocalDate dataInclusao;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name="INCIDENTE_PRODUTO",
            joinColumns={@JoinColumn(name="ID_INCIDENTE")},
            inverseJoinColumns={@JoinColumn(name="ID_PRODUTO")})
    private List<ProdutoModel> produtoModelList;

    @ToString.Exclude
    @ManyToMany(mappedBy = "incidenteModelList")
    private List<ProblemaModel> problemaModelList;
}