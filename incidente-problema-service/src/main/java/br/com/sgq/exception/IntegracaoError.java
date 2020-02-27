package br.com.sgq.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntegracaoError {
    private String timestamp;
    private Integer status;
    private String message;
    private String error;
    private String path;
    private List<ConstraintError> constraints;
}