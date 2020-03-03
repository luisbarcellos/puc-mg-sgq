package br.com.sgq.norma.v1.contract;

import br.com.sgq.norma.v1.contract.model.NormaInput;
import br.com.sgq.norma.v1.contract.model.NormaOutput;
import br.com.sgq.norma.v1.impl.binder.NormaBinder;
import br.com.sgq.norma.v1.impl.facade.NormaFacade;
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
        @Tag(name = "Normas", description = "Api de risco de normas")
})
public class NormaController {
    @Autowired
    private NormaFacade normaFacade;

    @PostMapping("normas")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere normas.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Norma inserido com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void inserirNorma(@Valid @RequestBody NormaInput normaInput) {
        normaFacade.inserirRiscoAcidente(NormaBinder.bindToModel(normaInput));
    }

    @PutMapping("normas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza uma norma.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Norma atualizada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarNorma(@PathVariable(value = "id") Long id, @RequestBody NormaInput normaInput) {
        normaFacade.atualizarNorma(id, NormaBinder.bindToModel(normaInput));
    }

    @PatchMapping("normas/{id-norma}/nao-conformidade/{id-nao-conformidade}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza uma norma vinculando uma não conformidade.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Norma atualizada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarNormaComNaoConformidade(@PathVariable(value = "id-norma") Long idNorma,
                                                 @PathVariable(value = "id-nao-conformidade") Long idNaoConformidade) {
        normaFacade.atualizarNormaComNaoConformidade(idNorma, idNaoConformidade);
    }

    @GetMapping("normas/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca uma norma.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public NormaOutput buscarRiscoAcidente(@PathVariable(value = "id") Long id) {
        return NormaBinder.bindToOutput(normaFacade.buscarNorma(id));
    }

    @GetMapping("normas")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos normas.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public List<NormaOutput> buscarNormas() {
        return NormaBinder.bindToOutputList(normaFacade.buscarNormas());
    }

    @DeleteMapping("normas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma norma.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Norma deletada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarNorma(@PathVariable(value = "id") Long id) {
        normaFacade.deletarNorma(id);
    }
}