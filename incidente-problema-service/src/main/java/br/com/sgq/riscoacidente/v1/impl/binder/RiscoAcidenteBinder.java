package br.com.sgq.riscoacidente.v1.impl.binder;

import br.com.sgq.riscoacidente.v1.contract.model.RiscoAcidenteInput;
import br.com.sgq.riscoacidente.v1.contract.model.RiscoAcidenteOutput;
import br.com.sgq.riscoacidente.v1.impl.model.RiscoAcidenteModel;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RiscoAcidenteBinder {
    private RiscoAcidenteBinder() {}

    public static RiscoAcidenteModel bindToModel(RiscoAcidenteInput riscoAcidenteInput) {
        return RiscoAcidenteModel.builder()
                .tipo(riscoAcidenteInput.getTipo())
                .descricao(riscoAcidenteInput.getDescricao())
                .build();
    }

    public static List<RiscoAcidenteOutput> bindToOutputList(List<RiscoAcidenteModel> riscoAcidenteModelList) {
        return riscoAcidenteModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(RiscoAcidenteBinder::bindToOutput)
                    .collect(Collectors.toList());
    }

    public static RiscoAcidenteOutput bindToOutput(RiscoAcidenteModel riscoAcidenteModel) {
        return Optional.ofNullable(riscoAcidenteModel)
                .map(riscoAcidente -> RiscoAcidenteOutput.builder()
                        .idRiscoAcidente(riscoAcidente.getIdRiscoAcidente())
                        .tipo(riscoAcidente.getTipo())
                        .descricao(riscoAcidente.getDescricao())
                        .build())
                .orElse(null);
    }
}