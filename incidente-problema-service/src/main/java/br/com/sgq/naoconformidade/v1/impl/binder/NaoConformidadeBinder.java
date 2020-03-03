package br.com.sgq.naoconformidade.v1.impl.binder;

import br.com.sgq.naoconformidade.v1.contract.model.NaoConformidadeInput;
import br.com.sgq.naoconformidade.v1.contract.model.NaoConformidadeOutput;
import br.com.sgq.naoconformidade.v1.impl.model.NaoConformidadeModel;
import br.com.sgq.problema.v1.impl.binder.ProblemaBinder;
import br.com.sgq.riscoacidente.v1.impl.binder.RiscoAcidenteBinder;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NaoConformidadeBinder {
    private NaoConformidadeBinder() {}

    public static NaoConformidadeModel bindToModel(NaoConformidadeInput naoConformidadeInput) {
        return NaoConformidadeModel.builder()
                .tipo(naoConformidadeInput.getTipo())
                .descricao(naoConformidadeInput.getDescricao())
                .build();
    }

    public static List<NaoConformidadeOutput> bindToOutputList(List<NaoConformidadeModel> naoConformidadeModelList) {
        return naoConformidadeModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(NaoConformidadeBinder::bindToOutput)
                    .collect(Collectors.toList());
    }

    public static NaoConformidadeOutput bindToOutput(NaoConformidadeModel naoConformidadeModel) {
        return Optional.ofNullable(naoConformidadeModel)
                .map(norma -> NaoConformidadeOutput.builder()
                        .idNaoConformidade(norma.getIdNaoConformidade())
                        .tipo(norma.getTipo())
                        .descricao(norma.getDescricao())
                        .riscoAcidente(RiscoAcidenteBinder.bindToOutput(norma.getRiscoAcidenteModel()))
                        .problema(ProblemaBinder.bindToOutputSemProduto(norma.getProblemaModel()))
                        .build())
                .orElse(null);
    }
}