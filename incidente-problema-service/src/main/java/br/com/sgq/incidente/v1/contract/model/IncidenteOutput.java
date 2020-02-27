package br.com.sgq.incidente.v1.contract.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class IncidenteOutput {
    private Long idIncidente;
    private String tipo;
    private String gravidade;
    private String descricao;
    private LocalDate dataInclusao;
}