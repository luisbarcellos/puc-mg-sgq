package br.com.sgq.problema.v1.impl.facade;

import br.com.sgq.incidente.v1.impl.facade.IncidenteFacade;
import br.com.sgq.problema.v1.impl.model.ProblemaModel;
import br.com.sgq.problema.v1.impl.service.ProblemaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProblemaFacade {
    private final IncidenteFacade incidenteFacade;
    private final ProblemaService problemaService;

    public Long inserirProblema(ProblemaModel problemaModel) {
        return problemaService.inserir(problemaModel);
    }

    public void atualizarProblema(Long idProblema, ProblemaModel problemaModel) {
        problemaService.atualizar(idProblema, problemaModel);
    }

    public void atualizarIncidente(Long idProblema, Long idIncidente) {
        problemaService.atualizarIncidente(idProblema, incidenteFacade.buscarIncidente(idIncidente));
    }

    public ProblemaModel buscarProblema(Long idProblema) {
        return problemaService.buscar(idProblema);
    }

    public List<ProblemaModel> buscarProblemas() {
        return problemaService.buscarTodos();
    }

    public void deletarProblema(Long idProblema) {
        problemaService.deletar(idProblema);
    }

    public void deletarIncidente(Long idProblema, Long idIncidente) {
        problemaService.deletarIncidente(idProblema, incidenteFacade.buscarIncidente(idIncidente));
    }
}