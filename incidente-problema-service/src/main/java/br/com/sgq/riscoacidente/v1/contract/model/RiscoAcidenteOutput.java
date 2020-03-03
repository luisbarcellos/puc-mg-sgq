package br.com.sgq.riscoacidente.v1.contract.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RiscoAcidenteOutput {
    private Long idRiscoAcidente;
    private String tipo;
    private String descricao;
}