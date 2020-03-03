package br.com.sgq.problema.v1.impl.service;

import br.com.sgq.exception.NotFoundException;
import br.com.sgq.exception.ValidacaoException;
import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import br.com.sgq.problema.v1.impl.model.ProblemaModel;
import br.com.sgq.problema.v1.impl.repository.ProblemaRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProblemaService {
    private static final String MESSAGE_NOT_FOUND = "Id do problema não encontrado.";
    private final ProblemaRepository problemaRepository;

    public Long inserir(ProblemaModel problemaModel) {
        return problemaRepository.save(problemaModel).getIdProblema();
    }

    public void atualizar(Long idProblema, ProblemaModel problemaModel) {
            buscar(idProblema);
            problemaRepository.atualizar(idProblema, problemaModel);
    }

    public void atualizarIncidente(Long idProblema, IncidenteModel incidenteModel) {
            ProblemaModel problemaModel = buscar(idProblema);
            problemaModel.getIncidenteModelList().add(incidenteModel);

        try {
            problemaRepository.save(problemaModel);
        }catch (DataIntegrityViolationException e) {
            throw new ValidacaoException("Problema já contém o incidente informado.");
        }
    }

    public ProblemaModel buscar(Long idProblema) {
        return problemaRepository.findById(idProblema)
                .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
    }

    public List<ProblemaModel> buscarTodos() {
        return problemaRepository.findAll();
    }

    public void deletarIncidente(Long idProblema, IncidenteModel incidenteModel) {
        ProblemaModel problemaModel = buscar(idProblema);
        if(!problemaModel.getIncidenteModelList().remove(incidenteModel)) {
            throw new ValidacaoException("Problema não possui o incidente informado.");
        }
        problemaRepository.save(problemaModel);
    }

    public void deletar(Long idProblema) {
        try {
            problemaRepository.deleteById(idProblema);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MESSAGE_NOT_FOUND);
        }
    }
}