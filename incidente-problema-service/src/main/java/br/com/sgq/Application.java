package br.com.sgq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.sgq.incidente.v1.impl.model"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}