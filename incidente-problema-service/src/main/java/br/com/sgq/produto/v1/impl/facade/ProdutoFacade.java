package br.com.sgq.produto.v1.impl.facade;

import br.com.sgq.produto.v1.impl.model.ProdutoModel;
import br.com.sgq.produto.v1.impl.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoFacade {
    private final ProdutoService produtoService;

    public Long inserirProduto(ProdutoModel produtoModel) {
        return produtoService.inserir(produtoModel);
    }

    public void atualizarProduto(Long idProduto, ProdutoModel produtoModel) {
        produtoService.atualizar(idProduto, produtoModel);
    }

    public ProdutoModel buscarProduto(Long idProduto) {
        return produtoService.buscar(idProduto);
    }

    public List<ProdutoModel> buscarProdutos() {
        return produtoService.buscarTodos();
    }

    public void deletarProduto(Long idProduto) {
        produtoService.deletar(idProduto);
    }
}