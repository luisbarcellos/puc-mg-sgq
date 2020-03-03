package br.com.sgq.problema.v1.contract.model;

import br.com.sgq.problema.v1.contract.model.input.ProblemaInput;
import br.com.sgq.problema.v1.contract.model.output.ProblemaOutput;
import br.com.sgq.problema.v1.impl.binder.ProblemaBinder;
import br.com.sgq.problema.v1.impl.facade.ProblemaFacade;
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
        @Tag(name = "Problema", description = "Api de problemas")
})
public class ProblemaController {
    @Autowired
    private ProblemaFacade problemaFacade;

    @PostMapping("problemas")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere problemas.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Problema inserido com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public Long inserirProblema(@Valid @RequestBody ProblemaInput problemaInput) {
        return problemaFacade.inserirProblema(ProblemaBinder.bindToModel(problemaInput));
    }

    @PutMapping("problemas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza um problema.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Problema atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarProblema(@PathVariable(value = "id") Long id, @RequestBody ProblemaInput problemaInput) {
        problemaFacade.atualizarProblema(id, ProblemaBinder.bindToModel(problemaInput));
    }

    @PatchMapping("problemas/{id-problema}/incidente/{id-incidente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza um problema adicionando um incidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Problema atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarProblemaComIncidente(@PathVariable(value = "id-problema") Long idProblema,
                                              @PathVariable(value = "id-incidente") Long idIncidente) {
        problemaFacade.atualizarIncidente(idProblema, idIncidente);
    }

    @GetMapping("problemas/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca um problema.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public ProblemaOutput buscarProblema(@PathVariable(value = "id") Long id) {
        return ProblemaBinder.bindToOutput(problemaFacade.buscarProblema(id));
    }

    @GetMapping("problemas")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos problemas.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public List<ProblemaOutput> buscarProblemas() {
        return ProblemaBinder.bindToOutputList(problemaFacade.buscarProblemas());
    }

    @DeleteMapping("problemas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um problema.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Problema deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarProblema(@PathVariable(value = "id") Long id) {
        problemaFacade.deletarProblema(id);
    }

    @DeleteMapping("problemas/{id-problema}/incidente/{id-incidente}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um problema.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Problema deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarProblema(@PathVariable(value = "id-problema") Long idProblema,
                                @PathVariable(value = "id-incidente") Long idIncidente) {
        problemaFacade.deletarIncidente(idProblema, idIncidente);
    }
}