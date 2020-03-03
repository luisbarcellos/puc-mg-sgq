package br.com.sgq.naoconformidade.v1.impl.model;

import br.com.sgq.problema.v1.impl.model.ProblemaModel;
import br.com.sgq.riscoacidente.v1.impl.model.RiscoAcidenteModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "NAO_CONFORMIDADE", schema = "public")
public class NaoConformidadeModel {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID_NAO_CONFORMIDADE")
    private Long idNaoConformidade;

    @NotNull
    @Column(name = "TIPO")
    private String tipo;

    @NotNull
    @Column(name = "DESCRICAO")
    private String descricao;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_RISCO_ACIDENTE", referencedColumnName = "ID_RISCO_ACIDENTE")
    private RiscoAcidenteModel riscoAcidenteModel;

    @ToString.Exclude
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PROBLEMA", referencedColumnName = "ID_PROBLEMA")
    private ProblemaModel problemaModel;
}