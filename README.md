# LiterAlura 📚

![Status](https://img.shields.io/badge/status-concluído-brightgreen)
![GitHub top language](https://img.shields.io/github/languages/top/MicheleLescano/literalura-challange-java)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 📖 Sobre o Projeto

O **LiterAlura** é uma aplicação de catálogo de livros desenvolvida como parte do desafio de programação do programa Alura ONE. A aplicação permite que os usuários interajam com um catálogo de livros através de um console, realizando buscas em uma API externa, salvando os resultados em um banco de dados e consultando as informações persistidas.

Este projeto foi desenvolvido para consolidar conhecimentos em **Java** e **Spring Boot**, aplicando conceitos de consumo de API, manipulação de JSON, persistência de dados com Spring Data JPA e modelagem de um menu interativo no console.

---

## ✨ Funcionalidades

A aplicação oferece um menu com as seguintes opções:

* ✅ **1 - Buscar livro pelo título:** Realiza uma busca na API Gutendex e salva o primeiro livro encontrado no banco de dados, evitando duplicidade.
* ✅ **2 - Listar livros registrados:** Exibe todos os livros que foram salvos no banco de dados.
* ✅ **3 - Listar autores registrados:** Mostra todos os autores salvos no banco de dados.
* ✅ **4 - Listar autores vivos em um determinado ano:** Permite ao usuário inserir um ano e exibe os autores que estavam vivos naquele período.
* ✅ **5 - Listar livros em um determinado idioma:** Exibe todos os livros registrados em um idioma específico (ex: `en`, `pt`, `es`).
* ✅ **0 - Sair:** Encerra a aplicação.

---

## 🎬 Demonstração em GIF

![Demo do LiterAlura em funcionamento](https://i.imgur.com/link-para-seu-gif.gif) 

---

## 🛠️ Tecnologias Utilizadas

As seguintes tecnologias foram utilizadas na construção do projeto:

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
* [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) ou superior.
* [Maven](https://maven.apache.org/download.cgi)
* [PostgreSQL](https://www.postgresql.org/download/)
* Uma IDE de sua preferência (ex: [IntelliJ IDEA](https://www.jetbrains.com/idea/download/), [Eclipse](https://www.eclipse.org/downloads/)).

### Rodando o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/MicheleLescano/literalura-challange-java.git](https://github.com/MicheleLescano/literalura-challange-java.git)
    ```

2.  **Configure o Banco de Dados:**
    * Abra o PostgreSQL e crie um novo banco de dados chamado `literalura`.

3.  **Configure as variáveis de ambiente:**
    * Na pasta `src/main/resources`, crie um arquivo chamado `application.properties`.
    * Adicione as seguintes linhas, substituindo `SEU_USUARIO` e `SUA_SENHA` pelas suas credenciais do PostgreSQL:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
        spring.datasource.username=SEU_USUARIO
        spring.datasource.password=SUA_SENHA
        spring.jpa.hibernate.ddl-auto=update
        ```

4.  **Execute a aplicação:**
    * Abra o projeto na sua IDE.
    * Localize a classe `LiteraluraApplication.java` e execute o método `main`.
    * A aplicação iniciará no console.

---

## 👩‍💻 Autora

**Michele Lescano**

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/michele-lescano-dev/)
[![github](https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/MicheleLescano)

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.md) para mais detalhes.
