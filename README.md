# API Web Service - Conversor Temperaturas - Grupo Acert
  Projeto em Spring Boot de uma construção API RESTFul voltado à atender o desafio Grupo Acert <link>https://www.grupoacert.com.br/.
   
  Uma solução criada em Java em formato de API REST que atenda aos requisitos para a recepção e/ou criação de conversões de temperaturas. Isto é, uma API no padrão RESTFUL que permita o usuário obter resultados de conversão de graus celsius 
  para fahrenheit e vice-versa mantendo as consultas realizadas num histórico, sendo este histórico acessível através de outra api.
 
 #### Stack do projeto
  - Escrito em Java 8;
  - Utilizando as facilidades e recursos framework Spring Boot;
  - Lombok na classes para evitar o boilerplate do Java;
  - Framework Hibernarte e Spring Data JPA para garantir a persistiência dos dados e facilitar as operações CRUD (aumentando o nivel de desempenho e escalabilidade);
  - Boas práticas de programação, utilizando Design Patterns (Builder, Factory, Strategy);
  - Testes unitátios (TDD/Mockito);
  - Banco de dados PostgreSQL;
  - Docker utilizando o compose;
  
  #### Visão Geral
  
  A aplicaçao tem como objetivo disponibilizar endpoints para consulta de informações e operações à respeito de:
  - Unidades e escalas termométricas;
  - Histórico de todas as conversões de temperaturas efetuadas pela API. 
  
  #### Instruções Inicialização
  
 O comando ```docker-compose up``` inicializará uma instância do Postgres 9.3, nesse momento será criada o schema ```conversor_service``` e suas respectivas tabelas no database ```postgres```. 
 Com a finalidade de gerenciar as escalas termométricas e o histórico de conversões efetuados pela API com informações necessárias para a demonstração do projeto. <br> Em seguida a aplicação de api-rest-conversor pode ser executada.
  
  ##### Endpoints: 
  
  Utilizando a ferramenta de documentação de endpoints ```Swagger```, pode-se visualizar todos os endpoints disponíveis.<br>
  Basta acessar a documentação da API por meio da URL <link>http://localhost:8080/swagger-ui.html , logo após a sua inicialização. <br><br> 
  De sorte que, segue a lista de alguns endpoints para conhecimento: 
  
  - Retornar uma lista completa de historico de conversões paginaveis (JSON/XML):
    - `http://localhost:8080/historico/`
    - `http://localhost:8080/historico/buscaPorPeriodo?dataInicio=01/01/2020&dataFim=31/10/2020`
    - `http://localhost:8080/historico/buscaValorResultadoIgualOuMenor?valor=50.00`
    - `http://localhost:8080/historico/buscaValorOrigemIgualOuMaior?valor=80.00`
   
 - Retornar uma lista completa de escalas em formatos (JSON/XML):
   - `http://localhost:8080/escala/`
   - `http://localhost:8080/escala/exibeTodosEmXML/`
   - `http://localhost:8080/escala/exibeTodosEmJSON/`
     
 Entre outros, aos quais podem ser identificados no endereço fornecido pelo Swagger: <link>http://localhost:8080/swagger-ui.html.