# MicroService Exemplo - Spring Boot & Docker

Este projeto é um exemplo prático de uma arquitetura de microsserviços utilizando **Java Spring Boot** e **MySQL**, orquestrados via **Docker Compose**. O objetivo é demonstrar como configurar, conteinerizar e conectar uma API REST a um banco de dados em um ambiente isolado.

## 🛠 Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 4.0.0 (Snapshot)**
* **Spring Data JPA (Hibernate)**
* **MySQL 8.0**
* **Docker & Docker Compose**
* **Maven**

## 📋 Pré-requisitos

Para rodar este projeto, você precisa ter instalado em sua máquina:

* [Docker](https://www.docker.com/get-started)
* [Docker Compose](https://docs.docker.com/compose/install/)
* [Java 17 JDK](https://adoptium.net/) & [Maven](https://maven.apache.org/) (Apenas se for rodar o build localmente fora do Docker multi-stage)

## 🚀 Como Rodar o Projeto

Siga os passos abaixo para colocar a aplicação no ar.

### Passo 1: Gerar o Build (Pacote)
Antes de subir os contêineres, é necessário gerar o arquivo `.jar` da aplicação (caso seu Dockerfile não seja multi-stage). Na raiz do projeto, execute:

```bash
mvn clean package -DskipTests
```
### Passo 2: Subir os Contêineres
Com o build finalizado, use o Docker Compose para criar e iniciar os serviços (API e Banco de Dados):

```bash
docker compose up --build
```

O parâmetro --buildgarante que a imagem seja recriada com as alterações mais recentes.

### Etapa 3: Verificar o status
Aguarde alguns instantes. O banco de dados MySQL demora alguns segundos para iniciar.

* API: Estará rodando em http://localhost:8080

* Banco de Dados: Acessível internamente na porta 3306.

Nota: Se a API falhar ao iniciar com erro de conexão (Communications link failure), aguarde o banco subir completamente e reinicie apenas a API: docker compose restart api.

## 🔌 Documentação da API
Abaixo estão os endpoints disponíveis no UserController.

### 1. Criar Usuário
Salva um novo usuário no banco de dados.

* URL: /users/save

* Método: POST

* Corpo da Requisição (JSON Exemplo):

```JSON
{
    "nome": "João Silva",
    "email": "joao@exemplo.com"
}
```
(Ajuste os campos conforme os atributos da sua classe User.java)

### 2. Listar Usuários
Retorna uma lista com todos os usuários cadastrados.

* URL: /users/buscar

* Método: GET

* Resposta (Exemplo):

```JSON
[
    {
        "id": 1,
        "nome": "João Silva",
        "email": "joao@exemplo.com"
    },
    {
        "id": 2,
        "nome": "Maria Souza",
        "email": "maria@exemplo.com"
    }
]
```

## 📂 Estrutura do Projeto (Docker)

O ambiente Docker é composto por dois serviços principais definidos no compose.yaml:

- api-microsserviço :

  * Container da aplicação Java Spring Boot.

  * Expõe a porta 8080.

  * Depende do serviço db.

- banco de dados mysql :

  * Container do banco de dados MySQL.

  * Persistência de dados configurada no volume mysql-data.

## 🐛 Solução de Problemas Comuns
Erro: "Falha ao carregar a classe do driver com.mysql.cj.jdbc.Driver"

* Isso ocorre quando o .jar dentro do container está desatualizado. Execute mvn clean package novamente e depois docker compose up --build.

Erro: Conexão recusada com o Banco

* Verifique se o container do MySQL está "Healthy" ou rodando. Certifique-se de que a URL no application.properties ou nas variáveis de ambiente do Docker aponta para jdbc:mysql://db:3306/... (onde "db" é o nome do serviço no compose).
