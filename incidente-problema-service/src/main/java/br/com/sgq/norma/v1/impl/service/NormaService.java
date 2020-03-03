package br.com.sgq.norma.v1.impl.service;

import br.com.sgq.norma.v1.impl.model.NormaModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NormaService {

    public void inserir(NormaModel normaModel) {
    }

    public void atualizar(Long idNorma, NormaModel normaModel) {
//            buscar(idNorma);
//            normaRepository.atualizar(idNorma, normaModel);
    }

    public void atualizarNaoConformidade(Long idNorma, Long idNaoConformidade) {
//            buscar(idNorma);
//            normaRepository.atualizarNaoConformidade(idNorma, idNaoConformidade);
    }

    public NormaModel buscar(Long idNorma) {
        return null;
//        return normaRepository.findById(idNorma)
//                .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
    }

    public List<NormaModel> buscarTodos() {
        return null;
    }

    public void deletar(Long idProduto) {
//        try {
//            normaRepository.deleteById(idProduto);
//        } catch (EmptyResultDataAccessException e) {
//            throw new NotFoundException(MESSAGE_NOT_FOUND);
//        }
    }
}