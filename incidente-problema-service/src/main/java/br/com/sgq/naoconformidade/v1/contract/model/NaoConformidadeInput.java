package br.com.sgq.naoconformidade.v1.contract.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NaoConformidadeInput {
    @ApiModelProperty(value = "Tipo de não conformidade.", required = true)
    @NotEmpty(message = "Tipo de não conformidade é obrigatório.")
    private String tipo;

    @ApiModelProperty(value = "Descrição da não conformidade.", required = true)
    @NotEmpty(message = "Descrição da não conformidade é obrigatório.")
    private String descricao;
}