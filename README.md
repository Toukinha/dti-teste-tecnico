# DTI – Teste Técnico (Books CLI)

Aplicação de linha de comando (CLI) para gerenciar livros (CRUD) usando **Java 17**, **Spring Boot 3**, **SQLite**, **JPA/Hibernate** e **Flyway**. A arquitetura segue princípios de **Clean Architecture**, mantendo o domínio desacoplado da infraestrutura.

---

## Visão Geral

- Menu interativo no terminal para **criar, listar, atualizar, remover** e **consultar livro por ID**.

- O SQLite é utilizado como banco local. O Flyway gerencia as migrações, garantindo o versionamento do schema e facilitando a adição ou remoção de tabelas e colunas.

---

## Tecnologias & Dependências Principais

- Java 17
- Spring Boot  (Data JPA)
- Hibernate / JPA
- SQLite
- Flyway&#x20;
- Lombok 

---

## Pré-requisitos

- Git (para clonar o repositório)
- Docker CLI (Linux) ou Docker Desktop (Windows/macOS)

## Como Executar com Docker

Com o Docker CLI ou o Docker Desktop instalado na sua máquina, execute no terminal na raiz do projeto:

```bash
docker compose run --rm -it books-cli
```

---

## Migrações com Flyway

Scripts SQL em `src/main/resources/db/migration`. Exemplo:

```sql
CREATE TABLE books (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    number_of_pages INTEGER NOT NULL,
    published_date TIMESTAMP
);
```

---

##

