package br.com.sgq.riscoacidente.v1.impl.model;

import br.com.sgq.naoconformidade.v1.impl.model.NaoConformidadeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "RISCO_ACIDENTE", schema = "public")
public class RiscoAcidenteModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_RISCO_ACIDENTE")
    private Long idRiscoAcidente;

    @NotNull
    @Column(name = "TIPO")
    private String tipo;

    @NotNull
    @Column(name = "DESCRICAO")
    private String descricao;

    @OneToOne(mappedBy = "riscoAcidenteModel")
    private NaoConformidadeModel naoConformidadeModel;
}