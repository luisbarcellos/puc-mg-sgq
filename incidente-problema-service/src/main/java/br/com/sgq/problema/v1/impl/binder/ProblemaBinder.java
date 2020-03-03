package br.com.sgq.problema.v1.impl.binder;

import br.com.sgq.incidente.v1.impl.binder.IncidenteBinder;
import br.com.sgq.problema.v1.contract.model.input.ProblemaInput;
import br.com.sgq.problema.v1.contract.model.output.ProblemaOutput;
import br.com.sgq.problema.v1.impl.model.ProblemaModel;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProblemaBinder {
    private ProblemaBinder() {}

    public static ProblemaModel bindToModel(ProblemaInput problemaInput) {
        return ProblemaModel.builder()
                .tipo(problemaInput.getTipo())
                .gravidade(problemaInput.getGravidade())
                .descricao(problemaInput.getDescricao())
                .dataInclusao(LocalDate.now())
                .build();
    }

    public static List<ProblemaOutput> bindToOutputList(List<ProblemaModel> problemaModelList) {
        return problemaModelList.stream()
                    .filter(list -> ! ObjectUtils.isEmpty(list))
                    .map(ProblemaBinder::bindToOutput)
                    .collect(Collectors.toList());
    }

    public static ProblemaOutput bindToOutput(ProblemaModel problemaModel) {
        return ProblemaOutput.builder()
                .idProblema(problemaModel.getIdProblema())
                .tipo(problemaModel.getTipo())
                .gravidade(problemaModel.getGravidade())
                .descricao(problemaModel.getDescricao())
                .dataInclusao(problemaModel.getDataInclusao())
                .incidentes(IncidenteBinder.bindToOutputListSemProdutoNoIncidente(problemaModel.getIncidenteModelList()))
                .build();
    }

    public static ProblemaOutput bindToOutputSemProduto(ProblemaModel problemaModel) {
        return Optional.ofNullable(problemaModel)
                .map(problema ->
                    ProblemaOutput.builder()
                    .idProblema(problema.getIdProblema())
                    .tipo(problema.getTipo())
                    .gravidade(problema.getGravidade())
                    .descricao(problema.getDescricao())
                    .dataInclusao(problema.getDataInclusao())
                    .build())
                .orElse(null);
    }
}