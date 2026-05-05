# Sistema de Agendamento

API REST desenvolvida com Spring Boot para gerenciamento de agendamentos médicos, pacientes e profissionais.

---

# Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- H2 Database
- Swagger/OpenAPI
- Maven
- JUnit + Mockito

---

# Como executar o projeto

## Pré-requisitos

Antes de iniciar, é necessário ter instalado:

- Java 21
- Maven
- IntelliJ IDEA (opcional)

---

## Clonar o projeto

```bash
git clone <url-do-repositorio>
```

---

## Executar a aplicação

Abra o projeto na IDE e execute a classe principal:

```text
TestepraticoApplication
```

Ou execute pelo terminal:

```bash
mvn spring-boot:run
```

---

# Acesso ao banco H2

Acesse:

```text
http://localhost:8080/h2-console
```

## Configuração do banco

### JDBC URL

```text
jdbc:h2:mem:testdb
```

### User

```text
sa
```

### Password

```text

```

---

# Documentação Swagger

A documentação da API pode ser acessada em:

```text
http://localhost:8080/swagger-ui.html
```

ou

```text
http://localhost:8080/swagger-ui/index.html
```

---

# Funcionalidades

- Cadastro de pacientes
- Cadastro de profissionais
- Criação de agendamentos
- Cancelamento de agendamentos
- Busca de agendamentos por:
  - paciente
  - profissional
  - status

---

# Exemplos de endpoints

## Criar agendamento

```http
POST /v1/agendamento
```

## Buscar por paciente

```http
GET /v1/agendamento/paciente/{nome}
```

## Buscar por profissional

```http
GET /v1/agendamento/profissional/{nome}
```

## Cancelar agendamento

```http
PATCH /v1/agendamento/{id}/cancelar
```

---

# Testes automatizados

O projeto possui testes automatizados utilizando:

- JUnit 5
- Mockito
