package br.com.sgq.incidente.v1.impl.service;

import br.com.sgq.exception.NotFoundException;
import br.com.sgq.exception.ValidacaoException;
import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import br.com.sgq.incidente.v1.impl.repository.IncidenteRepository;
import br.com.sgq.produto.v1.impl.model.ProdutoModel;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class IncidenteService {
    private static final String MESSAGE_NOT_FOUND = "Id do incidente não encontrado.";
    private final IncidenteRepository incidenteRepository;

    public Long inserir(IncidenteModel incidenteModel) {
        return incidenteRepository.save(incidenteModel).getIdIncidente();
    }

    public void atualizar(Long idIncidente, IncidenteModel incidenteModel) {
            buscar(idIncidente);
            incidenteRepository.atualizar(idIncidente, incidenteModel);
    }

    public void atualizarProduto(Long idIncidente, ProdutoModel produtoModel) {
        IncidenteModel incidenteModel = buscar(idIncidente);
        incidenteModel.getProdutoModelList().add(produtoModel);
        try {
            incidenteRepository.save(incidenteModel);
        }catch (DataIntegrityViolationException e) {
            throw new ValidacaoException("Incidente já contém o produto informado.");
        }
    }

    public IncidenteModel buscar(Long idIncidente) {
        return incidenteRepository.findById(idIncidente)
                .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
    }

    public List<IncidenteModel> buscarTodos() {
        return incidenteRepository.findAll();
    }

    public void deletar(Long idIncidente) {
        try {
            incidenteRepository.deleteById(idIncidente);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MESSAGE_NOT_FOUND);
        }
    }

    public void deletarProduto(Long idIncidente, ProdutoModel produtoModel) {
        IncidenteModel incidenteModel = buscar(idIncidente);
        if(!incidenteModel.getProdutoModelList().remove(produtoModel)) {
            throw new ValidacaoException("Incidente não possui o produto informado.");
        }
            incidenteRepository.save(incidenteModel);
    }
}