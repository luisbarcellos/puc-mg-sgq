# Arquitetura de Software Distribuído - TCC PUC Minas
Esse repositório possui o código fonte da POC(prova de conveito) do trabalho de conclusão do curso, da pós graduação em Arquitetura de Software Distribuído.

## Escopo do projeto
O escopo do projeto escolhido para o TCC foi o de Sistema de Gestão de Qualidade, voltado ao ramo automotivo.

## Descrição do projeto
O projeto é composto por 3 módulos armazenados de forma distribuída, em diferentes repositórios, e uma base de dados que subirá sepado em um docker.
* [Módulo Front End](https://github.com/luisbarcellos/frontend-module)
* [Módulo de Incidentes e Problemas](https://github.com/luisbarcellos/incidente-problema-service)
* [Módulo com Mock para Normas](https://github.com/luisbarcellos/mock-service)


## Tecnologias utilizadas

### Backend

* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [PostgreSQL](https://www.postgresql.org/download/)
* [Gradle](https://gradle.org/)

### Frontend

* [Angular](https://angular.io/)
* [Angular Cli](https://cli.angular.io/)
* [Bootstrap](https://getbootstrap.com/)
* [Nodejs](https://nodejs.org/en/)
* [Npm](https://www.npmjs.com/)

### Ambiente

* [Docker](https://www.docker.com/)

## Executando a aplicação

1. Necessário possuir instalado no equipamento a tecnologia [docker](https://www.docker.com/).
2. Baixar o arquivo docker-compose.yml deste repositório e executar o comando: docker-compose up
3. A aplicação poderá ser acessada através da URL: http://localhost:4200/