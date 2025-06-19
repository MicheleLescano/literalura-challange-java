# LiterAlura - Catálogo de Livros

Desafio de Java do programa Alura ONE, focado em construir uma aplicação de console para interagir com uma API de livros (Gutendex) e persistir os dados em um banco de dados PostgreSQL.

## Tecnologias Utilizadas
- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- API Gutendex

## Como Executar
1. Clone o repositório.
2. Crie um banco de dados PostgreSQL chamado `literalura`.
3. Na pasta `src/main/resources`, crie um arquivo `application.properties` e configure suas credenciais do banco:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=SEU_USUARIO_AQUI
   spring.datasource.password=SUA_SENHA_AQUI
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   spring.jpa.properties.hibernate.format_sql=true