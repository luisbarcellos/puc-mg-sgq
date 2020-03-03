package br.com.sgq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.sgq.incidente.v1.impl.model",
                            "br.com.sgq.produto.v1.impl.model",
                            "br.com.sgq.problema.v1.impl.model",
                            "br.com.sgq.riscoacidente.v1.impl.model",
                            "br.com.sgq.naoconformidade.v1.impl.model"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}