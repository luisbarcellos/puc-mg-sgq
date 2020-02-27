package br.com.sgq.incidente.v1.contract;

import br.com.sgq.incidente.v1.contract.model.IncidenteInput;
import br.com.sgq.incidente.v1.contract.model.IncidenteOutput;
import br.com.sgq.incidente.v1.impl.binder.IncidenteBinder;
import br.com.sgq.incidente.v1.impl.facade.IncidenteFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@Api(tags = "v1")
@Validated
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
    public void inserirIncidente(@Valid @RequestBody IncidenteInput incidenteInput) {
        incidenteFacade.inserirIncidente(IncidenteBinder.bindToModel(incidenteInput));
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

    @DeleteMapping("incidentes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um incidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Incidente deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void desfazerTransferencia(@PathVariable(value = "id") Long id) {
        incidenteFacade.deletarIncidente(id);
    }
}