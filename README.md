# CodeBank - Loan Eligibility API 💰

Esta é uma API REST robusta e escalável desenvolvida em **Java** com **Spring Boot**, projetada para analisar a elegibilidade de linhas de crédito (empréstimos) para clientes com base em perfis financeiros, idade e localização geográfica.

## 🚀 Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **PostgreSQL** (Banco de dados relacional robusto para persistência de dados)
- **Validation (Jakarta)** (Validação sintática de payloads)

## 🏛️ Pilares de Arquitetura & Design Patterns
O sistema foi desenhado priorizando a resiliência, manutenibilidade e a aplicação rigorosa de boas práticas de engenharia de software:

- **Global Exception Handler (`@RestControllerAdvice`)**: Centralização do tratamento de exceções da camada de infraestrutura. Captura falhas de validação (`MethodArgumentNotValidException`) e erros de negócio, convertendo-os em respostas padronizadas em formato de lista, blindando a aplicação contra vazamento de stacktraces.
- **Fail-Fast & Validação em Camadas**: Uso do `@Valid` em conjunto com DTOs para validações sintáticas imediatas. A consistência dos dados é reforçada por restrições de integridade física no banco de dados (`unique = true` para o CPF) e validações lógicas na camada de serviço antes de qualquer persistência.
- **Princípio DRY (Don't Repeat Yourself)**: Lógica booleana unificada utilizando precedência de operadores lógicos para agrupar regras de negócio idênticas, evitando duplicidade de dados no payload de retorno e eliminando redundâncias no código.

## 📋 Regras de Negócio Implementadas
1. **Empréstimo Pessoal (`PERSONAL`)**: Concedido se a renda for $\le$ R$ 3000, ou se a renda estiver entre R$ 3000 e R$ 5000, contanto que o cliente tenha menos de 30 anos e resida em São Paulo (SP). (Taxa de juros: 4%)
2. **Empréstimo com Garantia (`GUARANTEED`)**: Segue exatamente os mesmos critérios de elegibilidade do Empréstimo Pessoal. (Taxa de juros: 3%)
3. **Empréstimo Consignado (`CONSIGNMENT`)**: Concedido se a renda do cliente for $\ge$ R$ 5000. (Taxa de juros: 2%)

---

## 🧪 Como Testar a API

### Endpoint: `POST /loans`

#### 1. Cenário: Cliente Elegível a Múltiplos Empréstimos (Renda $\le$ R$ 3000)
**Request Body:**
```json
{
  "name": "Alex Silva",
  "cpf": "111.111.111-11",
  "age": 35,
  "income": 2500.0,
  "location": "RJ"
}

```

**Response Body (200 OK):**

```json
{
  "customer": "Alex Silva",
  "loans": [
    { "type": "PERSONAL", "interestRate": 4 },
    { "type": "GUARANTEED", "interestRate": 3 }
  ]
}

```

#### 2. Cenário: Cliente Elegível a Empréstimo Consignado (Renda $\ge$ R$ 5000)

**Request Body:**

```json
{
  "name": "Beatriz Souza",
  "cpf": "222.222.222-22",
  "age": 40,
  "income": 6000.0,
  "location": "MG"
}

```

**Response Body (200 OK):**

```json
{
  "customer": "Beatriz Souza",
  "loans": [
    { "type": "CONSIGNMENT", "interestRate": 2 }
  ]
}

```

#### 3. Cenário de Resiliência: Validação de CPF Duplicado

Ao tentar submeter uma requisição com um CPF já existente na base de dados, a camada de serviço interrompe o fluxo de processamento e o Handler Global formata a saída:

**Response Body (400 Bad Request):**

```json
[
  "CPF ja cadastrado"
]

```

---

Desenvolvido por [Matheus Delmones](https://www.google.com/search?q=https://github.com/matheusdelmones).
