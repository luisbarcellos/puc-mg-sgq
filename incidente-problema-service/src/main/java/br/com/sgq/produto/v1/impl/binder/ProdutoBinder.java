package br.com.sgq.produto.v1.impl.binder;

import br.com.sgq.incidente.v1.impl.binder.IncidenteBinder;
import br.com.sgq.produto.v1.contract.model.ProdutoInput;
import br.com.sgq.produto.v1.contract.model.ProdutoOutput;
import br.com.sgq.produto.v1.impl.model.ProdutoModel;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoBinder {
    private ProdutoBinder() {}

    public static ProdutoModel bindToModel(ProdutoInput produtoInput) {
        return ProdutoModel.builder()
                .nome(produtoInput.getNome())
                .tipo(produtoInput.getTipo())
                .descricao(produtoInput.getDescricao())
                .build();
    }

    public static List<ProdutoOutput> bindToOutputList(List<ProdutoModel> produtoModelList) {
        return produtoModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(ProdutoBinder::bindToOutputSemProdutoNoIncidente)
                    .collect(Collectors.toList());
    }

    public static List<ProdutoOutput> bindToOutputListSemIncidentes(List<ProdutoModel> produtoModelList) {
        return produtoModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(ProdutoBinder::bindToOutputSemIncidentes)
                    .collect(Collectors.toList());
    }

    public static ProdutoOutput bindToOutput(ProdutoModel produtoModel) {
        return ProdutoOutput.builder()
                .idProduto(produtoModel.getIdProduto())
                .nome(produtoModel.getNome())
                .tipo(produtoModel.getTipo())
                .descricao(produtoModel.getDescricao())
                .build();
    }

    public static ProdutoOutput bindToOutputSemProdutoNoIncidente(ProdutoModel produtoModel) {
        return ProdutoOutput.builder()
                .idProduto(produtoModel.getIdProduto())
                .nome(produtoModel.getNome())
                .tipo(produtoModel.getTipo())
                .descricao(produtoModel.getDescricao())
                .build();
    }

    public static ProdutoOutput bindToOutputSemIncidentes(ProdutoModel produtoModel) {
        return ProdutoOutput.builder()
                .idProduto(produtoModel.getIdProduto())
                .nome(produtoModel.getNome())
                .tipo(produtoModel.getTipo())
                .descricao(produtoModel.getDescricao())
                .build();
    }
}