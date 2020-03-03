package br.com.sgq.produto.v1.impl.service;

import br.com.sgq.exception.NotFoundException;
import br.com.sgq.produto.v1.impl.model.ProdutoModel;
import br.com.sgq.produto.v1.impl.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProdutoService {
    private static final String MESSAGE_NOT_FOUND = "Id do produto não encontrado.";
    private final ProdutoRepository produtoRepository;

    public Long inserir(ProdutoModel produtoModel) {
        return produtoRepository.save(produtoModel).getIdProduto();
    }

    public void atualizar(Long idProduto, ProdutoModel produtoModel) {
            buscar(idProduto);
            produtoRepository.atualizar(idProduto, produtoModel);
    }

    public ProdutoModel buscar(Long idProduto) {
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
    }

    public List<ProdutoModel> buscarTodos() {
        return produtoRepository.findAll();
    }

    public void deletar(Long idProduto) {
        try {
            produtoRepository.deleteById(idProduto);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MESSAGE_NOT_FOUND);
        }
    }
}