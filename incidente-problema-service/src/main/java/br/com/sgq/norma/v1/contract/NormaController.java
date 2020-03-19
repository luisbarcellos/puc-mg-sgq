package br.com.sgq.norma.v1.contract;

import br.com.sgq.norma.v1.contract.model.NormaOutput;
import br.com.sgq.norma.v1.impl.binder.NormaBinder;
import br.com.sgq.norma.v1.impl.facade.NormaFacade;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}