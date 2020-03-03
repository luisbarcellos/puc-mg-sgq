package br.com.sgq.produto.v1.impl.repository;

import br.com.sgq.produto.v1.impl.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE PRODUTO" +
                   "   SET NOME = :#{#produtoModel.nome}," +
                   "       TIPO = :#{#produtoModel.tipo}," +
                   "       DESCRICAO = :#{#produtoModel.descricao}" +
                   " WHERE ID_PRODUTO = :idProduto", nativeQuery = true)
    void atualizar(@Param("idProduto") Long idProduto,
                   @Param("produtoModel") ProdutoModel produtoModel);
}