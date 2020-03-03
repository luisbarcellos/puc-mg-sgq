package br.com.sgq.riscoacidente.v1.impl.facade;

import br.com.sgq.riscoacidente.v1.impl.model.RiscoAcidenteModel;
import br.com.sgq.riscoacidente.v1.impl.service.RiscoAcidenteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RiscoAcidenteFacade {
    private final RiscoAcidenteService riscoAcidenteService;

    public void inserirRiscoAcidente(RiscoAcidenteModel riscoAcidenteModel) {
        riscoAcidenteService.inserir(riscoAcidenteModel);
    }

    public void atualizarRiscoAcidente(Long idRiscoAcidente, RiscoAcidenteModel riscoAcidenteModel) {
        riscoAcidenteService.atualizar(idRiscoAcidente, riscoAcidenteModel);
    }

    public RiscoAcidenteModel buscarRiscoAcidente(Long idRiscoAcidente) {
        return riscoAcidenteService.buscar(idRiscoAcidente);
    }

    public List<RiscoAcidenteModel> buscarRiscosAcidentes() {
        return riscoAcidenteService.buscarTodos();
    }

    public void deletarRiscoAcidente(Long idRiscoAcidente) {
        riscoAcidenteService.deletar(idRiscoAcidente);
    }
}