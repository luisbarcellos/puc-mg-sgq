package br.com.sgq.incidente.v1.impl.binder;

import br.com.sgq.incidente.v1.contract.model.IncidenteInput;
import br.com.sgq.incidente.v1.contract.model.IncidenteOutput;
import br.com.sgq.incidente.v1.impl.model.IncidenteModel;

import java.time.LocalDate;

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

    public static IncidenteOutput bindToOutput(IncidenteModel incidenteModel) {
        return IncidenteOutput.builder()
                .idIncidente(incidenteModel.getIdIncidente())
                .tipo(incidenteModel.getTipo())
                .gravidade(incidenteModel.getGravidade())
                .descricao(incidenteModel.getDescricao())
                .dataInclusao(incidenteModel.getDataInclusao())
                .build();
    }
}