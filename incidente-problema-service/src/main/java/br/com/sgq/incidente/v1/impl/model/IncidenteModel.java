package br.com.sgq.incidente.v1.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
}