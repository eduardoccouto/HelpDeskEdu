# 📚 Spring Boot Learning API

Uma API para gerenciamento de chamados de helpdesk, desenvolvida com o objetivo de consolidar conhecimentos em **Java** e **Spring Boot**. O projeto foca em boas práticas de desenvolvimento backend, organização de código, uso de dependências modernas e integração com banco de dados relacional.

## 🚀 Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- H2 / MySQL
- Lombok
- Swagger / OpenAPI
- Maven

## 🎯 Objetivos Educacionais

- Criação de endpoints RESTful
- Estruturação de projetos com Spring Boot
- Persistência de dados com JPA/Hibernate
- Implementação de DTOs, Services e Repositories
- Documentação de API com Swagger

## 📌 Endpoints da API

**Base URL:** `http://localhost:8080/`

---

### `GET /api/v2/chamados`

**Parâmetros de consulta (opcionais):**

- `titulo`: string  
- `id`: long  
- `prioridade`: enum `PrioridadeChamado`  
- `status`: enum `StatusChamado`  
- `nome_usuario`: string  
- `pagina`: integer (padrão: 0)  
- `tamanhoPagina`: integer (padrão: 10)

**Exemplo de resposta:**

```json
{
  "content": [
    {
      "id": 8,
      "titulo": "Falha no salvamento",
      "prioridade": "ALTO",
      "status": "ABERTO",
      "usuario": {
        "nome": "EDUARDO",
        "username": "ntidudu",
        "email": "email@email.com"
      },
      "descricao": "Erro ao tentar salvar formulário"
    }
  ],
  "page": {
    "size": 1,
    "number": 0,
    "totalElements": 1,
    "totalPages": 1
  }
}
```

---

### `PUT /api/v1/chamados/{id}`

Atualiza um chamado existente. O ID do chamado deve ser passado na URL.

**Corpo da requisição:**

```json
{
  "descricao": "Erro ao tentar salvar formulário"
}
```

**Respostas possíveis:**

- `201 Created` – Atualização bem-sucedida  
- `400 Bad Request`  
```json
{
  "status": 400,
  "message": "string",
  "errors": [
    {
      "campo": "string",
      "erro": "string"
    }
  ]
}
```

---

### `DELETE /api/v1/chamados/{id}`

Remove um chamado do banco de dados.

**Respostas possíveis:**

- `204 No Content` – Exclusão bem-sucedida  
- `404 Not Found` – Chamado não encontrado

---

### `POST /api/v1/chamados`

Cria um novo chamado.

**Corpo da requisição:**

```json
{
  "titulo": "string",
  "descricao": "string",
  "prioridade": "BAIXO"
}
```

**Respostas possíveis:**

- `201 Created`  
- `400 Bad Request`  
```json
{
  "status": 400,
  "message": "string",
  "errors": [
    {
      "campo": "string",
      "erro": "string"
    }
  ]
}
```

---

### `GET /api/v1/chamados/meus-chamados`

Lista os chamados do usuário autenticado.

**Parâmetros de paginação (opcionais):**

- `page`: integer (padrão: 0)  
- `size`: integer (padrão: 10)

**Exemplo de resposta:**

```json
{
  "content": [
    {
      "id": 8,
      "titulo": "Falha no salvamento",
      "prioridade": "ALTO",
      "status": "ABERTO",
      "usuario": {
        "nome": "EDUARDO",
        "username": "ntidudu",
        "email": "email@email.com"
      },
      "descricao": "Erro ao tentar salvar formulário"
    }
  ],
  "page": {
    "size": 10,
    "number": 0,
    "totalElements": 17,
    "totalPages": 2
  }
}
```

---

### `POST /api/v1/chamados/auth`

Autenticação do usuário.

**Corpo da requisição:**

```json
{
  "username": "string",
  "password": "string"
}
```

**Respostas possíveis:**

- `200 OK` – Autenticado  
- `401 Unauthorized` – Falha na autenticação

---

### `POST /api/v1/usuarios/cadastro`

Cadastro de novo usuário.

**Corpo da requisição:**

```json
{
  "nome": "string",
  "username": "string",
  "password": "string",
  "email": "string",
  "funcao": ["ADM"]
}
```

**Resposta esperada:**

| Código | Descrição |
|--------|-----------|
| 200    | OK        |