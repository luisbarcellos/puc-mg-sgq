package br.com.sgq.produto.v1.contract.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdutoInput {
    @ApiModelProperty(value = "Nome do produto.", required = true)
    @NotEmpty(message = "Nome do produto é obrigatório.")
    private String nome;

    @ApiModelProperty(value = "Tipo de produto.", required = true)
    @NotEmpty(message = "Tipo de incidente é obrigatório.")
    private String tipo;

    @ApiModelProperty(value = "Descrição do produto.", required = true)
    @NotEmpty(message = "Descrição do produto é obrigatório.")
    private String descricao;
}