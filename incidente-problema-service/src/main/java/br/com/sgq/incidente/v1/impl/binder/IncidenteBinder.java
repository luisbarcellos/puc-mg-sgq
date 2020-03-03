package br.com.sgq.incidente.v1.impl.binder;

import br.com.sgq.incidente.v1.contract.model.IncidenteInput;
import br.com.sgq.incidente.v1.contract.model.IncidenteOutput;
import br.com.sgq.incidente.v1.impl.model.IncidenteModel;
import br.com.sgq.produto.v1.impl.binder.ProdutoBinder;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class IncidenteBinder {
    private IncidenteBinder() {}

    public static IncidenteModel bindToModel(IncidenteInput incidenteInput) {
        return IncidenteModel.builder()
                .tipo(incidenteInput.getTipo())
                .gravidade(incidenteInput.getGravidade())
                .descricao(incidenteInput.getDescricao())
                .dataInclusao(LocalDate.now())
                .build();
    }

    public static List<IncidenteOutput> bindToOutputList(List<IncidenteModel> incidenteModelList) {
        return incidenteModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(IncidenteBinder::bindToOutput)
                    .collect(Collectors.toList());
    }

    public static List<IncidenteOutput> bindToOutputListSemProdutoNoIncidente(List<IncidenteModel> incidenteModelList) {
        return incidenteModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(IncidenteBinder::bindToOutputSemProduto)
                    .collect(Collectors.toList());
    }

    public static IncidenteOutput bindToOutput(IncidenteModel incidenteModel) {
        return IncidenteOutput.builder()
                .idIncidente(incidenteModel.getIdIncidente())
                .tipo(incidenteModel.getTipo())
                .gravidade(incidenteModel.getGravidade())
                .descricao(incidenteModel.getDescricao())
                .dataInclusao(incidenteModel.getDataInclusao())
                .produtos(ProdutoBinder.bindToOutputListSemIncidentes(incidenteModel.getProdutoModelList()))
                .build();
    }

    public static IncidenteOutput bindToOutputSemProduto(IncidenteModel incidenteModel) {
        return IncidenteOutput.builder()
                .idIncidente(incidenteModel.getIdIncidente())
                .tipo(incidenteModel.getTipo())
                .gravidade(incidenteModel.getGravidade())
                .descricao(incidenteModel.getDescricao())
                .dataInclusao(incidenteModel.getDataInclusao())
                .build();
    }
}