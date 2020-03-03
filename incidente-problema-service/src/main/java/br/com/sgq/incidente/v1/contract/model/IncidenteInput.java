package br.com.sgq.incidente.v1.contract.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IncidenteInput {
    @ApiModelProperty(value = "Tipo de incidente.", required = true)
    @NotEmpty(message = "Tipo de incidente é obrigatório.")
    private String tipo;

    @ApiModelProperty(value = "Gravidade do incidente.", required = true)
    @NotEmpty(message = "Gravidade do incidente é obrigatório.")
    private String gravidade;

    @ApiModelProperty(value = "Descrição do incidente.", required = true)
    @NotEmpty(message = "Descrição do incidente é obrigatório.")
    private String descricao;
}