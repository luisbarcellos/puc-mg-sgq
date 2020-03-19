package br.com.sgq.norma.v1.impl.service;

import br.com.sgq.exception.NotFoundException;
import br.com.sgq.norma.v1.impl.model.NormaModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class NormaService {
    private static final String MESSAGE_NOT_FOUND = "Id da norma não encontrado.";
    private List<NormaModel> normaModelList= new ArrayList();

    public NormaService() {
        normaModelList.add(
                NormaModel.builder()
                        .idNorma(1L)
                        .tipo("QS9000")
                        .descricao("O QS9000 era um padrão de qualidade desenvolvido por um esforço conjunto " +
                                "das \" Três Grandes \" montadoras americanas , General Motors , Chrysler e Ford . " +
                                "Foi introduzido no setor em 1994. Também foi adotado por vários fabricantes de " +
                                "caminhões pesados nos EUA . Essencialmente, todos os fornecedores das montadoras " +
                                "americanas precisavam implementar um sistema QS9000 padrão antes de seu término.")
                        .impactoErgonomia("N/A")
                        .dataInclusao(LocalDate.now())
                        .build());

        normaModelList.add(
                NormaModel.builder()
                        .idNorma(2L)
                        .tipo("VDA 6")
                        .descricao("O VDA 6.1 é um padrão de sistema de gerenciamento de qualidade alemão. " +
                                "Foi iniciado pela indústria automobilística. O primeiro padrão VDA foi para a " +
                                "troca de modelos de superfície e foi nomeado \" VDA-FS \". Foi substituída ao longo " +
                                "do caminho por um subconjunto da \"Especificação inicial de troca de gráficos\" ( IGES )" +
                                "; referido simplesmente como \"VDA\". Além desse padrão de troca, a VDA também " +
                                "desenvolveu o \" VDA-PS \", um padrão de biblioteca para peças padrão, agora conhecido" +
                                " como \" DIN 66304 \".")
                        .impactoErgonomia("N/A")
                        .dataInclusao(LocalDate.now())
                        .build());
    }

    public void inserir(NormaModel normaModel) {
        normaModel.setIdNorma(Long.valueOf(normaModelList.size()) + 1);
        normaModelList.add(normaModel);
    }

    public void atualizar(Long idNorma, NormaModel normaModel) {
            normaModelList.remove(buscar(idNorma));
            normaModelList.add(normaModel);
    }

    public NormaModel buscar(Long idNorma) {
        return normaModelList.stream()
                .filter(normaModel -> normaModel.getIdNorma() == idNorma)
                .findAny()
                .orElseThrow(() -> new NotFoundException(MESSAGE_NOT_FOUND));
    }

    public List<NormaModel> buscarTodos() {
        return normaModelList;
    }

    public void deletar(Long idNorma) {
        normaModelList.stream()
                .filter(normaModel -> normaModel.getIdNorma() == idNorma)
                .findAny()
                .ifPresentOrElse(normaModel -> normaModelList.remove(normaModel),
                        () -> new NotFoundException(MESSAGE_NOT_FOUND));
    }
}