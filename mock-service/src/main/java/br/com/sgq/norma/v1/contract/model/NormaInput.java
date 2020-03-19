package br.com.sgq.norma.v1.contract.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NormaInput {
    @ApiModelProperty(value = "Tipo de norma.", required = true)
    @NotEmpty(message = "Tipo de norma é obrigatório.")
    private String tipo;

    @ApiModelProperty(value = "Descrição do risco de acidente.", required = true)
    @NotEmpty(message = "Descrição do risco de acidente é obrigatório.")
    private String descricao;

    @ApiModelProperty(value = "Descrição do impacto da ergonomia.", required = true)
    @NotEmpty(message = "Descrição do impacto da ergonomia é obrigatório.")
    private String impactoErgonomia;
}