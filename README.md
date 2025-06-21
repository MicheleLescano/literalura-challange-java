# LiterAlura 📚

![Status](https://img.shields.io/badge/status-concluído-brightgreen)
![GitHub top language](https://img.shields.io/github/languages/top/MicheleLescano/literalura-challange-java)
![License](https://img.shields.io/github/license/MicheleLescano/literalura-challange-java)

## 📖 Sobre o Projeto

O **LiterAlura** é um catálogo de livros interativo desenvolvido como parte do desafio de programação da Alura. A aplicação permite aos usuários buscar livros através da API pública Gutendex, manipular os dados recebidos em formato JSON, e persistir as informações em um banco de dados PostgreSQL. Toda a interação com o usuário é feita via console.

Este projeto foi uma oportunidade para consolidar e aplicar conhecimentos em **Java** e no ecossistema **Spring Boot**, com foco em:
- Criação de uma aplicação de console robusta.
- Consumo de APIs externas.
- Mapeamento de dados com a biblioteca Jackson.
- Persistência de dados com Spring Data JPA.
- Modelagem de dados e relacionamentos entre entidades.
- Boas práticas de organização de código em pacotes.

---

## ✨ Funcionalidades

O menu principal oferece 5 opções de interação com o catálogo de livros:

- ✅ **1 - Buscar livro pelo título:** Realiza uma busca na API Gutendex, salva o livro e o autor no banco de dados (evitando duplicidade) e exibe os detalhes do livro encontrado.
- ✅ **2 - Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados local.
- ✅ **3 - Listar autores registrados:** Mostra todos os autores salvos, junto com seus livros já cadastrados.
- ✅ **4 - Listar autores vivos em um determinado ano:** Permite ao usuário inserir um ano e exibe uma lista de autores que estavam vivos naquele período.
- ✅ **5 - Listar livros em um determinado idioma:** Exibe todos os livros registrados em um idioma específico escolhido pelo usuário (`en`, `pt`, `es`, `fr`).

---

## 🎬 Demonstração em GIF

![Demo do LiterAlura em funcionamento](https://i.imgur.com/N3XCHL0.gif)

---

## 🛠️ Tecnologias Utilizadas

As seguintes ferramentas e tecnologias foram utilizadas na construção do projeto:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
- [Maven](https://maven.apache.org/download.cgi)
- [PostgreSQL](https://www.postgresql.org/download/)
- Uma IDE de sua preferência (ex: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), [Eclipse](https://www.eclipse.org/downloads/)).

### Rodando o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/MicheleLescano/literalura-challange-java.git](https://github.com/MicheleLescano/literalura-challange-java.git)
    ```

2.  **Acesse a pasta do projeto:**
    ```bash
    cd literalura-challange-java
    ```
3.  **Configure o Banco de Dados:**
    - Abra o PostgreSQL e crie um novo banco de dados chamado `literalura`.
    - Na pasta `src/main/resources`, abra o arquivo `application.properties`.
    - Altere as seguintes linhas com o seu usuário e senha do PostgreSQL:
        ```properties
        spring.datasource.username=SEU_USUARIO
        spring.datasource.password=SUA_SENHA
        ```

4.  **Execute a aplicação:**
    - Abra o projeto na sua IDE.
    - Localize a classe `LiteraluraApplication.java` e execute o método `main`.
    - A aplicação iniciará e o menu interativo aparecerá no console.

---

## 👩‍💻 Autora

**Michele Lescano**

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/michele-lescano-dev/)
[![github](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/MicheleLescano/)