package br.com.sgq.naoconformidade.v1.impl.facade;

import br.com.sgq.exception.ValidacaoException;
import br.com.sgq.naoconformidade.v1.impl.model.NaoConformidadeModel;
import br.com.sgq.naoconformidade.v1.impl.service.NaoConformidadeService;
import br.com.sgq.problema.v1.impl.facade.ProblemaFacade;
import br.com.sgq.riscoacidente.v1.impl.facade.RiscoAcidenteFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NaoConformidadeFacade {
    private final RiscoAcidenteFacade riscoAcidenteFacade;
    private final ProblemaFacade problemaFacade;
    private final NaoConformidadeService naoConformidadeService;

    public Long inserirNaoConformidade(NaoConformidadeModel naoConformidadeModel) {
        return naoConformidadeService.inserir(naoConformidadeModel);
    }

    public void atualizarNaoConformidade(Long idNaoConformidade, NaoConformidadeModel naoConformidadeModel) {
        naoConformidadeService.atualizar(idNaoConformidade, naoConformidadeModel);
    }

    public void atualizarNaoConformidadeComProblema(Long idNaoConformidade, Long idProblema) {
        problemaFacade.buscarProblema(idProblema);
        naoConformidadeService.atualizarProblema(idNaoConformidade, idProblema);
    }

    public void atualizarNaoConformidadeComRiscoAcidente(Long idNaoConformidade, Long idRiscoAcidente) {
        riscoAcidenteFacade.buscarRiscoAcidente(idRiscoAcidente);
        if(!buscarNaoConformidadeComRiscoAcidente(idRiscoAcidente).isEmpty()) {
                throw  new ValidacaoException("Risco de acidente já associado a um problema.");
        }
        naoConformidadeService.atualizarRiscoAcidente(idNaoConformidade, idRiscoAcidente);
    }

    public NaoConformidadeModel buscarNaoConformidade(Long idNaoConformidade) {
        return naoConformidadeService.buscar(idNaoConformidade);
    }

    private Optional<NaoConformidadeModel> buscarNaoConformidadeComRiscoAcidente(Long idRiscoAcidente) {
        return naoConformidadeService.buscarComRiscoAcidente(idRiscoAcidente);
    }

    public List<NaoConformidadeModel> buscarNaoConformidades() {
        return naoConformidadeService.buscarTodos();
    }

    public void deletarNaoConformidade(Long idNaoConformidade) {
        naoConformidadeService.deletar(idNaoConformidade);
    }

    public void deletarProblema(Long idNaoConformidade, Long idProblema) {
        naoConformidadeService.deletarProblema(idNaoConformidade, problemaFacade.buscarProblema(idProblema));
    }

    public void deletarRiscoAcidente(Long idNaoConformidade, Long idRiscoAcidente) {
        naoConformidadeService.deletarRiscoAcidente(idNaoConformidade, riscoAcidenteFacade.buscarRiscoAcidente(idRiscoAcidente));
    }
}