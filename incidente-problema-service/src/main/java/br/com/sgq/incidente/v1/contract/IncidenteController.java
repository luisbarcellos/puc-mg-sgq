package br.com.sgq.incidente.v1.contract;

import br.com.sgq.incidente.v1.contract.model.IncidenteInput;
import br.com.sgq.incidente.v1.contract.model.IncidenteOutput;
import br.com.sgq.incidente.v1.impl.binder.IncidenteBinder;
import br.com.sgq.incidente.v1.impl.facade.IncidenteFacade;
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
        @Tag(name = "Incidente", description = "Api de incidente")
})
public class IncidenteController {
    @Autowired
    private IncidenteFacade incidenteFacade;

    @PostMapping("incidentes")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere incidentes.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Incidente inserido com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public Long inserirIncidente(@Valid @RequestBody IncidenteInput incidenteInput) {
        return incidenteFacade.inserirIncidente(IncidenteBinder.bindToModel(incidenteInput));
    }

    @PutMapping("incidentes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza um incidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Incidente atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarIncidente(@PathVariable(value = "id") Long id, @RequestBody IncidenteInput incidenteInput) {
        incidenteFacade.atualizarIncidente(id, IncidenteBinder.bindToModel(incidenteInput));
    }

    @PatchMapping("incidentes/{id-incidente}/produto/{id-produto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza um incidente adicionando um produto.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Incidente atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarProduto(@PathVariable(value = "id-incidente") Long idIncidente,
                                 @PathVariable(value = "id-produto") Long idProduto) {
        incidenteFacade.atualizarProduto(idIncidente, idProduto);
    }

    @GetMapping("incidentes/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca um incidente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public IncidenteOutput buscarIncidente(@PathVariable(value = "id") Long id) {
        return IncidenteBinder.bindToOutput(incidenteFacade.buscarIncidente(id));
    }

    @GetMapping("incidentes")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos incidentes.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public List<IncidenteOutput> buscarIncidentes() {
        return IncidenteBinder.bindToOutputList(incidenteFacade.buscarIncidentes());
    }

    @DeleteMapping("incidentes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um incidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Incidente deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarIncidente(@PathVariable(value = "id") Long id) {
        incidenteFacade.deletarIncidente(id);
    }

    @DeleteMapping("incidentes/{id-incidente}/produto/{id-produto}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um produto de um incidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Produto deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarProduto(@PathVariable(value = "id-incidente") Long idIncidente,
                                 @PathVariable(value = "id-produto") Long idProduto) {
        incidenteFacade.deletarProduto(idIncidente, idProduto);
    }
}