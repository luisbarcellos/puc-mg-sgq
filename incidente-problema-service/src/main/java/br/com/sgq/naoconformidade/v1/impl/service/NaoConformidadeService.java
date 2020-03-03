package br.com.sgq.naoconformidade.v1.impl.service;

import br.com.sgq.exception.NotFoundException;
import br.com.sgq.exception.ValidacaoException;
import br.com.sgq.naoconformidade.v1.impl.model.NaoConformidadeModel;
import br.com.sgq.naoconformidade.v1.impl.repository.NaoConformidadeRepository;
import br.com.sgq.problema.v1.impl.model.ProblemaModel;
import br.com.sgq.riscoacidente.v1.impl.model.RiscoAcidenteModel;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NaoConformidadeService {
    private static final String MESSAGE_NOT_FOUND = "Id da não conformidade não encontrado.";
    private final NaoConformidadeRepository naoConformidadeRepository;

    public Long inserir(NaoConformidadeModel naoConformidadeModel) {
        return naoConformidadeRepository.save(naoConformidadeModel).getIdNaoConformidade();
    }

    public void atualizar(Long idNaoConformidade, NaoConformidadeModel naoConformidadeModel) {
            buscar(idNaoConformidade);
            naoConformidadeRepository.atualizar(idNaoConformidade, naoConformidadeModel);
    }

    public void atualizarProblema(Long idNaoConformidade, Long idProblema) {
            buscar(idNaoConformidade);
            naoConformidadeRepository.atualizarProblema(idNaoConformidade, idProblema);
    }

    public void atualizarRiscoAcidente(Long idNaoConformidade, Long idRiscoAcidente) {
            buscar(idNaoConformidade);
            naoConformidadeRepository.atualizarRiscoAcidente(idNaoConformidade, idRiscoAcidente);
    }

    public NaoConformidadeModel buscar(Long idNaoConformidade) {
        return naoConformidadeRepository.findById(idNaoConformidade)
                .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
    }

    public Optional<NaoConformidadeModel> buscarComRiscoAcidente(Long idRiscoAcidente) {
        return naoConformidadeRepository.buscarNaoConformidadePorRiscoAcidente(idRiscoAcidente);
    }

    public List<NaoConformidadeModel> buscarTodos() {
        return naoConformidadeRepository.findAll();
    }

    public void deletarProblema(Long idNaoConformidade, ProblemaModel problemaModel) {
        NaoConformidadeModel naoConformidadeModel = buscar(idNaoConformidade);

        if(ObjectUtils.isEmpty(naoConformidadeModel.getProblemaModel())) {
            throw new ValidacaoException("Não conformidade não possui o problema informado.");
        }

        Optional.ofNullable(naoConformidadeModel.getProblemaModel())
                .ifPresent(problema-> {
                    if(!problema.equals(problemaModel)) {
                        throw new ValidacaoException("Não conformidade não possui o problema informado.");
                }});
        naoConformidadeModel.setProblemaModel(null);
        inserir(naoConformidadeModel);
    }

    public void deletarRiscoAcidente(Long idNaoConformidade, RiscoAcidenteModel riscoAcidenteModel) {
        NaoConformidadeModel naoConformidadeModel = buscar(idNaoConformidade);

        if(ObjectUtils.isEmpty(naoConformidadeModel.getRiscoAcidenteModel())) {
            throw new ValidacaoException("Não conformidade não possui o risco de acidente informado.");
        }

        Optional.ofNullable(naoConformidadeModel.getRiscoAcidenteModel())
                .ifPresent(riscoAcidente-> {
                    if(!riscoAcidente.equals(riscoAcidenteModel)) {
                        throw new ValidacaoException("Não conformidade não possui o risco de acidente informado.");
                }});
        naoConformidadeModel.setRiscoAcidenteModel(null);
        inserir(naoConformidadeModel);
    }

    public void deletar(Long idNaoConformidade) {
        try {
            naoConformidadeRepository.deleteById(idNaoConformidade);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(MESSAGE_NOT_FOUND);
        }
    }
}