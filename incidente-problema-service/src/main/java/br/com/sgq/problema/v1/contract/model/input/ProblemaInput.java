package br.com.sgq.problema.v1.contract.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProblemaInput {
    @ApiModelProperty(value = "Tipo de problema.", required = true)
    @NotEmpty(message = "Tipo de problema é obrigatório.")
    private String tipo;

    @ApiModelProperty(value = "Gravidade do problema.", required = true)
    @NotEmpty(message = "Gravidade do problema é obrigatório.")
    private String gravidade;

    @ApiModelProperty(value = "Descrição do problema.", required = true)
    @NotEmpty(message = "Descrição do problema é obrigatório.")
    private String descricao;
}