package br.com.sgq.riscoacidente.v1.contract.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RiscoAcidenteInput {
    @ApiModelProperty(value = "Tipo de risco de acidente.", required = true)
    @NotEmpty(message = "Tipo de risco de acidente é obrigatório.")
    private String tipo;

    @ApiModelProperty(value = "Descrição do risco de acidente.", required = true)
    @NotEmpty(message = "Descrição do risco de acidente é obrigatório.")
    private String descricao;
}