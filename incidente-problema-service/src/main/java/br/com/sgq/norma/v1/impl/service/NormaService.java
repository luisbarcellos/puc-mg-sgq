package br.com.sgq.norma.v1.impl.service;

import br.com.sgq.norma.v1.config.PropertiesLoader;
import br.com.sgq.norma.v1.impl.model.NormaModel;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@AllArgsConstructor
@Service
public class NormaService{
    private static final String URL_MOCK_SERVICE = "http://%s:8081/mock-service/v1/normas";

    private RestTemplate restTemplate;
    private PropertiesLoader propertiesLoader;

    public NormaModel buscar(Long idNorma) {
        return null;
//        Mock para buscar uma norma
    }

    public List<NormaModel> buscarTodos() {
        return restTemplate.exchange(getMockAppUrl(),
                                     HttpMethod.GET,
                                    null,
                                    new ParameterizedTypeReference<List<NormaModel>>() {}).getBody();
    }

    private String getMockAppUrl(){
        return String.format(URL_MOCK_SERVICE, propertiesLoader.getMockAppName());
    }
}