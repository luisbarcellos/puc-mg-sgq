package br.com.sgq.incidente.v1.impl.facade;

import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import br.com.sgq.incidente.v1.impl.service.IncidenteService;
import br.com.sgq.produto.v1.impl.facade.ProdutoFacade;
import br.com.sgq.produto.v1.impl.model.ProdutoModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@AllArgsConstructor
@Service
public class IncidenteFacade {
    private final ProdutoFacade produtoFacade;
    private final IncidenteService incidenteService;

    public Long inserirIncidente(IncidenteModel incidenteModel) {
        return incidenteService.inserir(incidenteModel);
    }

    public void atualizarIncidente(Long idIncidente, IncidenteModel incidenteModel) {
        incidenteService.atualizar(idIncidente, incidenteModel);
    }

    public void atualizarProduto(Long idIncidente, Long idProduto) {
        incidenteService.atualizarProduto(idIncidente, produtoFacade.buscarProduto(idProduto));
    }

    public IncidenteModel buscarIncidente(Long idIncidente) {
        return incidenteService.buscar(idIncidente);
    }

    public List<IncidenteModel> buscarIncidentes() {
        return incidenteService.buscarTodos();
    }

    public void deletarIncidente(Long idIncidente) {
        incidenteService.deletar(idIncidente);
    }

    public void deletarProduto(Long idIncidente, Long idProduto) {
        incidenteService.deletarProduto(idIncidente, produtoFacade.buscarProduto(idProduto));
    }
}