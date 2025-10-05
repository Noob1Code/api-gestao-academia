# 🏋️ API de Gerenciamento de Academia

Esta é uma API RESTful desenvolvida em **Java com Spring Boot** para gerenciar as operações de uma academia, incluindo o cadastro de alunos, planos, treinos e o registro de pagamentos.

---

## 🛠 Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.3.5**
* **Spring Data JPA**
* **Spring Web**
* **H2 Database** (Banco de dados em memória)
* **Maven** (Gerenciador de dependências)
* **SpringDoc OpenAPI** (Swagger UI para documentação)

---

## 🚀 Como Executar o Projeto

### 1. Pré-requisitos

* ☕ JDK 21 ou superior instalado
* 🧰 Maven instalado e configurado nas variáveis de ambiente

### 2. Clonar o repositório

```bash
git clone https://github.com/Noob1Code/api-gestao-academia
cd ProvaAcademiaKayque
```

### 3. Executar a aplicação

Use o wrapper do Maven para compilar e iniciar o servidor Spring Boot:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:
👉 [http://localhost:8088](http://localhost:8088)

---

## 🌐 Swagger UI

A forma mais simples de usar a API é pelo Swagger UI, que lista e permite testar todos os endpoints diretamente no navegador.

👉 [http://localhost:8088/swagger-ui.html](http://localhost:8088/swagger-ui.html)

---

## 📝 Ordem Sugerida de Operações

Para evitar erros de dependência entre entidades, siga a ordem abaixo para cadastrar os dados.

### 1. Cadastro de Planos e Treinos

Comece cadastrando os **Planos** e **Treinos**, pois serão usados no cadastro de alunos.

#### ➕ Cadastrar Plano

**POST** `/api/v1/planos`

```json
{
  "nome": "Plano Mensal Básico",
  "valor": 99.90
}
```

#### ➕ Cadastrar Treino

**POST** `/api/v1/treinos`

```json
{
  "nome": "Treino de Adaptação",
  "descricao": "Foco em exercícios multiarticulares para iniciantes.",
  "nivel": "INICIANTE"
}
```

---

### 2. Cadastro de Alunos

Após ter ao menos um plano cadastrado:

**POST** `/api/v1/alunos`

```json
{
  "nome": "João da Silva",
  "cpf": "12345678901",
  "dataNascimento": "2000-05-15",
  "planoId": 1
}
```

---

### 3. Vincular Treinos a Alunos

**POST** `/api/v1/alunos/vincular-treino`

```json
{
  "alunoId": 1,
  "treinoId": 1
}
```

---

### 4. Registrar Pagamentos

**POST** `/api/v1/pagamentos/aluno/{alunoId}`
Exemplo:

```
POST /api/v1/pagamentos/aluno/1
```

*(Não precisa de corpo na requisição)*

---

## 📌 Endpoints da API

### 📄 Planos (`/api/v1/planos`)

| Método | Endpoint | Descrição                |
| ------ | -------- | ------------------------ |
| POST   | `/`      | Cria um novo plano       |
| GET    | `/`      | Lista todos os planos    |
| GET    | `/{id}`  | Busca plano por ID       |
| PUT    | `/{id}`  | Atualiza plano existente |
| DELETE | `/{id}`  | Deleta plano             |

---

### 🏋 Treinos (`/api/v1/treinos`)

| Método | Endpoint | Descrição                 |
| ------ | -------- | ------------------------- |
| POST   | `/`      | Cria um novo treino       |
| GET    | `/`      | Lista todos os treinos    |
| GET    | `/{id}`  | Busca treino por ID       |
| PUT    | `/{id}`  | Atualiza treino existente |
| DELETE | `/{id}`  | Deleta treino             |

---

### 👤 Alunos (`/api/v1/alunos`)

| Método | Endpoint           | Descrição                    |
| ------ | ------------------ | ---------------------------- |
| POST   | `/`                | Cria um novo aluno           |
| GET    | `/`                | Lista todos os alunos        |
| GET    | `/{id}`            | Busca aluno por ID           |
| PUT    | `/{id}`            | Atualiza aluno existente     |
| PATCH  | `/{id}/inativar`   | Inativa o status de um aluno |
| POST   | `/vincular-treino` | Vincula um treino a um aluno |

---

### 💳 Pagamentos (`/api/v1/pagamentos`)

| Método | Endpoint           | Descrição                                |
| ------ | ------------------ | ---------------------------------------- |
| POST   | `/aluno/{alunoId}` | Registra um novo pagamento para um aluno |
| GET    | `/aluno/{alunoId}` | Lista todos os pagamentos de um aluno    |

---

## ⚖️ Regras de Negócio

✅ **CPF único:** Não é possível cadastrar dois alunos com o mesmo CPF
❌ **Exclusão de treino:** Não é permitido excluir treinos vinculados a alunos

---

## 📂 Estrutura Sugerida do Projeto

```bash
src/
 └── main/
     ├── java/
     │   └── com/
     │       └── academia/
     │           ├── controller/
     │           ├── service/
     │           ├── repository/
     │           ├── dto/
     │           └── model/
     └── resources/
         ├── application.properties
         └── data.sql
```

---

## 📣 Observação Importante

A dúvida “**qual entidade cadastrar primeiro?**” é crucial para usar corretamente a API.
👉 A resposta é: **comece por Planos e Treinos**, pois eles são pré-requisitos para cadastrar Alunos.

---

## 🧑‍💻 Autor

Desenvolvido por **Kayque de Freitas** ✨
