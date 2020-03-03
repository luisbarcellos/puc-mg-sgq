package br.com.sgq.riscoacidente.v1.contract;

import br.com.sgq.riscoacidente.v1.contract.model.RiscoAcidenteInput;
import br.com.sgq.riscoacidente.v1.contract.model.RiscoAcidenteOutput;
import br.com.sgq.riscoacidente.v1.impl.binder.RiscoAcidenteBinder;
import br.com.sgq.riscoacidente.v1.impl.facade.RiscoAcidenteFacade;
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
        @Tag(name = "Risco acidente", description = "Api de risco de acidente")
})
public class RiscoAcidenteController {
    @Autowired
    private RiscoAcidenteFacade riscoAcidenteFacade;

    @PostMapping("risco-acidentes")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Insere risco de acidente.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Risco de acidente inserido com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void inserirRiscoAcidente(@Valid @RequestBody RiscoAcidenteInput riscoAcidenteInput) {
        riscoAcidenteFacade.inserirRiscoAcidente(RiscoAcidenteBinder.bindToModel(riscoAcidenteInput));
    }

    @PutMapping("risco-acidentes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Atualiza um risco de acidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Risco de acidente atualizado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void atualizarRiscoAcidente(@PathVariable(value = "id") Long id, @RequestBody RiscoAcidenteInput riscoAcidenteInput) {
        riscoAcidenteFacade.atualizarRiscoAcidente(id, RiscoAcidenteBinder.bindToModel(riscoAcidenteInput));
    }

    @GetMapping("risco-acidentes/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca um risco de acidente.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public RiscoAcidenteOutput buscarRiscoAcidente(@PathVariable(value = "id") Long id) {
        return RiscoAcidenteBinder.bindToOutput(riscoAcidenteFacade.buscarRiscoAcidente(id));
    }

    @GetMapping("risco-acidentes")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Busca todos riscos de acidentes.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Busca efetuada com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public List<RiscoAcidenteOutput> buscarRiscosAcidentes() {
        return RiscoAcidenteBinder.bindToOutputList(riscoAcidenteFacade.buscarRiscosAcidentes());
    }

    @DeleteMapping("risco-acidentes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Deleta um risco de acidente.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Risco de acidente deletado com sucesso."),
            @ApiResponse(code = 400, message = "Requisição inválida."),
            @ApiResponse(code = 404, message = "Informação não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno.")})
    public void deletarRiscoAcidente(@PathVariable(value = "id") Long id) {
        riscoAcidenteFacade.deletarRiscoAcidente(id);
    }
}