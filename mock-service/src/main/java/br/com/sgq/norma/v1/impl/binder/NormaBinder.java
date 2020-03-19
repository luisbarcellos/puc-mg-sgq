package br.com.sgq.norma.v1.impl.binder;

import br.com.sgq.norma.v1.contract.model.NormaInput;
import br.com.sgq.norma.v1.contract.model.NormaOutput;
import br.com.sgq.norma.v1.impl.model.NormaModel;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class NormaBinder {
    private NormaBinder() {}

    public static NormaModel bindToModel(NormaInput normaInput) {
        return NormaModel.builder()
                .tipo(normaInput.getTipo())
                .descricao(normaInput.getDescricao())
                .impactoErgonomia(normaInput.getImpactoErgonomia())
                .dataInclusao(LocalDate.now())
                .build();
    }

    public static List<NormaOutput> bindToOutputList(List<NormaModel> normaModelList) {
        return normaModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(NormaBinder::bindToOutput)
                    .collect(Collectors.toList());
    }

    public static NormaOutput bindToOutput(NormaModel normaModel) {
        return NormaOutput.builder()
                    .idNorma(normaModel.getIdNorma())
                    .tipo(normaModel.getTipo())
                    .descricao(normaModel.getDescricao())
                    .dataInclusao(normaModel.getDataInclusao())
                    .impactoErgonomia(normaModel.getImpactoErgonomia())
                    .build();
    }
}