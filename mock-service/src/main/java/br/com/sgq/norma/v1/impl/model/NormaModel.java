package br.com.sgq.norma.v1.impl.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NormaModel {
    private Long idNorma;
    private String tipo;
    private String descricao;
    private String impactoErgonomia;
    private LocalDate dataInclusao;
}