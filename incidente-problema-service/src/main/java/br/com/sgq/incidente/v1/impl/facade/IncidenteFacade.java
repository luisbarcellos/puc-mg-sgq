package br.com.sgq.incidente.v1.impl.facade;

import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import br.com.sgq.incidente.v1.impl.service.IncidenteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IncidenteFacade {
    private final IncidenteService incidenteService;

    public void inserirIncidente(IncidenteModel incidenteModel) {
        incidenteService.inserir(incidenteModel);
    }

    public void atualizarIncidente(Long idIncidente, IncidenteModel incidenteModel) {
        incidenteService.atualizar(idIncidente, incidenteModel);
    }

    public IncidenteModel buscarIncidente(Long idIncidente) {
        return incidenteService.buscar(idIncidente);
    }

    public void deletarIncidente(Long idIncidente) {
        incidenteService.deletar(idIncidente);
    }
}