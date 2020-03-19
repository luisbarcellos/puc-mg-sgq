package br.com.sgq.norma.v1.impl.facade;

import br.com.sgq.norma.v1.impl.model.NormaModel;
import br.com.sgq.norma.v1.impl.service.NormaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NormaFacade {
    private final NormaService normaService;

    public void inserirRiscoAcidente(NormaModel normaModel) {
        normaService.inserir(normaModel);
    }

    public void atualizarNorma(Long idNorma, NormaModel normaModel) {
        normaService.atualizar(idNorma, normaModel);
    }

    public NormaModel buscarNorma(Long idNorma) {
        return normaService.buscar(idNorma);
    }

    public List<NormaModel> buscarNormas() {
        return normaService.buscarTodos();
    }

    public void deletarNorma(Long idRiscoAcidente) {
        normaService.deletar(idRiscoAcidente);
    }
}