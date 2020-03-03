package br.com.sgq.produto.v1.contract;

import br.com.sgq.produto.v1.contract.model.ProdutoInput;
import br.com.sgq.produto.v1.contract.model.ProdutoOutput;
import br.com.sgq.produto.v1.impl.binder.ProdutoBinder;
import br.com.sgq.produto.v1.impl.facade.ProdutoFacade;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
@Validated
@CrossOrigin(origins = "*")
@SwaggerDefinition(tags = {
        @Tag(name = "Produto", description = "Api de produto")
})
public class ProdutoController {
    @Autowired
    private ProdutoFacade produtoFacade;

    @PostMapping("produtos")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere produtos.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Produto inserido com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public Long inserirProduto(@Valid @RequestBody ProdutoInput produtoInput) {
        return produtoFacade.inserirProduto(ProdutoBinder.bindToModel(produtoInput));
    }

    @PutMapping("produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza um produto.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarProduto(@PathVariable(value = "id") Long id, @RequestBody ProdutoInput produtoInput) {
        produtoFacade.atualizarProduto(id, ProdutoBinder.bindToModel(produtoInput));
    }

    @GetMapping("produtos/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca um produto.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public ProdutoOutput buscarProduto(@PathVariable(value = "id") Long id) {
        return ProdutoBinder.bindToOutput(produtoFacade.buscarProduto(id));
    }

    @GetMapping("produtos")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos produtos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public List<ProdutoOutput> buscarProdutos() {
        return ProdutoBinder.bindToOutputList(produtoFacade.buscarProdutos());
    }

    @DeleteMapping("produtos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um produto.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarProduto(@PathVariable(value = "id") Long id) {
        produtoFacade.deletarProduto(id);
    }
}