package br.com.sgq.incidente.v1.impl.service;

import br.com.sgq.exception.NotFoundException;
import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import br.com.sgq.incidente.v1.impl.repository.IncidenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class IncidenteService {
    private final IncidenteRepository incidenteRepository;

    public void inserir(IncidenteModel incidenteModel) {
        incidenteRepository.save(incidenteModel);
    }

    public void atualizar(Long idIncidente, IncidenteModel incidenteModel) {
            buscar(idIncidente);
            incidenteRepository.atualizar(idIncidente, incidenteModel);
    }

    public IncidenteModel buscar(Long idIncidente) {
        return incidenteRepository.findById(idIncidente)
                .orElseThrow(() -> new NotFoundException("Id informado não encontrado."));
    }

    public void deletar(Long idIncidente) {
        try {
            incidenteRepository.deleteById(idIncidente);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("Id informado não encontrado.");
        }
    }
}