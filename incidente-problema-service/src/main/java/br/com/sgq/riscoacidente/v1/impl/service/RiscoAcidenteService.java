package br.com.sgq.riscoacidente.v1.impl.service;

import br.com.sgq.exception.NotFoundException;
import br.com.sgq.riscoacidente.v1.impl.model.RiscoAcidenteModel;
import br.com.sgq.riscoacidente.v1.impl.repository.RiscoAcidenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RiscoAcidenteService {
    private static final String MESSAGE_NOT_FOUND = "Id do risco de acidente não encontrado.";
    private final RiscoAcidenteRepository riscoAcidenteRepository;

    public void inserir(RiscoAcidenteModel riscoAcidenteModel) {
        riscoAcidenteRepository.save(riscoAcidenteModel);
    }

    public void atualizar(Long idRiscoAcidente, RiscoAcidenteModel riscoAcidenteModel) {
            buscar(idRiscoAcidente);
            riscoAcidenteRepository.atualizar(idRiscoAcidente, riscoAcidenteModel);
    }

    public RiscoAcidenteModel buscar(Long idRiscoAcidente) {
        return riscoAcidenteRepository.findById(idRiscoAcidente)
                .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
    }

    public List<RiscoAcidenteModel> buscarTodos() {
        return riscoAcidenteRepository.findAll();
    }

    public void deletar(Long idRiscoAcidente) {
        try {
            riscoAcidenteRepository.deleteById(idRiscoAcidente);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MESSAGE_NOT_FOUND);
        }
    }
}