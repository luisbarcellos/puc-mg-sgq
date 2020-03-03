package br.com.sgq.naoconformidade.v1.contract;

import br.com.sgq.naoconformidade.v1.contract.model.NaoConformidadeInput;
import br.com.sgq.naoconformidade.v1.contract.model.NaoConformidadeOutput;
import br.com.sgq.naoconformidade.v1.impl.binder.NaoConformidadeBinder;
import br.com.sgq.naoconformidade.v1.impl.facade.NaoConformidadeFacade;
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
        @Tag(name = "Não conformidade", description = "Api de não conformidade")
})
public class NaoConformidadeController {
    @Autowired
    private NaoConformidadeFacade naoConformidadeFacade;

    @PostMapping("nao-conformidades")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere não conformidade.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Não conformidade inserido com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public Long inserirNaoConformidade(@Valid @RequestBody NaoConformidadeInput naoConformidadeInput) {
        return naoConformidadeFacade.inserirNaoConformidade(NaoConformidadeBinder.bindToModel(naoConformidadeInput));
    }

    @PutMapping("nao-conformidades/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza uma não conformidade.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Não conformidade atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarNaoConformidade(@PathVariable(value = "id") Long id, @RequestBody NaoConformidadeInput naoConformidadeInput) {
        naoConformidadeFacade.atualizarNaoConformidade(id, NaoConformidadeBinder.bindToModel(naoConformidadeInput));
    }

    @PatchMapping("nao-conformidades/{id-nao-conformidade}/problema/{id-problema}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza uma não conformidade adicionando um problema.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Não conformidade atualizada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarNaoConformidadeComProblema(@PathVariable(value = "id-nao-conformidade") Long idNaoConformidade,
                                                    @PathVariable(value = "id-problema") Long idProblema) {
        naoConformidadeFacade.atualizarNaoConformidadeComProblema(idNaoConformidade, idProblema);
    }

    @PatchMapping("nao-conformidades/{id-nao-conformidade}/risco-acidente/{id-risco-acidente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza uma não conformidade adicionando um risco de acidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Não conformidade atualizada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarNaoConformidadeComRiscoAcidente(@PathVariable(value = "id-nao-conformidade") Long idNaoConformidade,
                                                         @PathVariable(value = "id-risco-acidente") Long idRiscoAcidente) {
        naoConformidadeFacade.atualizarNaoConformidadeComRiscoAcidente(idNaoConformidade, idRiscoAcidente);
    }

    @GetMapping("nao-conformidades/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca uma não conformidade.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public NaoConformidadeOutput buscarNaoConformidade(@PathVariable(value = "id") Long id) {
        return NaoConformidadeBinder.bindToOutput(naoConformidadeFacade.buscarNaoConformidade(id));
    }

    @GetMapping("nao-conformidades")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos não conformidades.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public List<NaoConformidadeOutput> buscarNaoConformidades() {
        return NaoConformidadeBinder.bindToOutputList(naoConformidadeFacade.buscarNaoConformidades());
    }

    @DeleteMapping("nao-conformidades/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta uma não conformidade.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Não conformidade deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarNaoConformidade(@PathVariable(value = "id") Long id) {
        naoConformidadeFacade.deletarNaoConformidade(id);
    }

    @DeleteMapping("nao-conformidades/{id-nao-conformidade}/problema/{id-problema}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove um problema de uma não conformidade.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Problema removido com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarProblema(@PathVariable(value = "id-nao-conformidade") Long idNaoConformidade,
                                @PathVariable(value = "id-problema") Long idProblema) {
        naoConformidadeFacade.deletarProblema(idNaoConformidade, idProblema);
    }

    @DeleteMapping("nao-conformidades/{id-nao-conformidade}/risco-acidente/{id-risco-acidente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Remove um risco de acidente de uma não conformidade.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Não conformidade removida com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarRiscoAcidente(@PathVariable(value = "id-nao-conformidade") Long idNaoConformidade,
                                       @PathVariable(value = "id-risco-acidente") Long idRiscoAcidente) {
        naoConformidadeFacade.deletarRiscoAcidente(idNaoConformidade, idRiscoAcidente);
    }
}