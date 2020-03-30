package br.com.sgq.norma.v1.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
@NoArgsConstructor
public class PropertiesLoader {
    @Value("${mockapp.name}")
    private String mockAppName;
}