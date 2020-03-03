package br.com.sgq.problema.v1.impl.model;

import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import br.com.sgq.naoconformidade.v1.impl.model.NaoConformidadeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PROBLEMA", schema = "public")
public class ProblemaModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_PROBLEMA")
    private Long idProblema;

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
    @JoinTable(name="PROBLEMA_INCIDENTE",
            joinColumns={@JoinColumn(name="ID_PROBLEMA")},
            inverseJoinColumns={@JoinColumn(name="ID_INCIDENTE")})
    private List<IncidenteModel> incidenteModelList;

    @OneToOne(mappedBy = "problemaModel")
    private NaoConformidadeModel naoConformidadeModel;
}