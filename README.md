# 🏋️ Plataforma Fitness Diário

> **Arquitetura Full-Stack Desacoplada para Geração Inteligente de Treinos e Dietas**

Esta plataforma foi desenvolvida para solucionar um desafio crítico na modelagem de sistemas de saúde e performance: a gestão de dados heterogêneos e personalizados. Utilizando o banco de dados orientado a documentos **Couchbase**, a aplicação centraliza o perfil do aluno, suas métricas antropométricas, restrições médicas e o planejamento completo de 5 dias em um único documento JSON aninhado, eliminando a necessidade de junções (*joins*) complexas ou tabelas relacionais com campos nulos.

---

## 🛠️ Tecnologias e Ecossistema

| Camada | Tecnologia | Propósito |
| :--- | :--- | :--- |
| **Frontend** | HTML5 / CSS3 / JavaScript | Interface de usuário limpa, assíncrona (Fetch API) e desacoplada. |
| **Backend** | Java 21 / Spring Boot 4.x | API REST robusta estruturada com Spring Data Couchbase. |
| **Persistência**| Couchbase Server 7.2.0 | Banco de Dados Orientado a Documentos (NoSQL). |
| **Infraestrutura**| Docker / Docker Compose | Containerização e padronização do ambiente local. |
| **Produtividade** | Lombok | Eliminação de código boilerplate (Getters, Setters, Construtores). |

---
## 🧠 Arquitetura e Padrões de Projeto

### 📐 O Padrão Comportamental Strategy
Para evitar o acoplamento e o surgimento de estruturas condicionais extensas (`if/else` ou `switch`) na camada de negócio, foi implementado o padrão **Strategy**. 

As regras de cálculo calórico e distribuição de treinos foram isoladas em componentes especializados (`HipertrofiaStrategy` e `EmagrecimentoStrategy`). O Spring Boot injeta essas estratégias dinamicamente em um mapa em tempo de execução:

```java
// O comportamento se adapta dinamicamente com base no objetivo informado pelo cliente
ObjetivoStrategy strategy = strategies.get(usuario.getObjetivo());
strategy.montarPlano(usuario);
```
---

## ⚙️ Inicialização e Configuração do Ambiente

### 1. Subindo o Banco de Dados (Docker)
Certifique-se de que o Docker Desktop está em execução. Na raiz do projeto backend (onde está o arquivo `docker-compose.yml`), execute o comando para iniciar o servidor Couchbase em segundo plano:

```bash
docker-compose up -d
```

### 2. Preparação do Cluster Couchbase

* Acesse o console administrativo do banco em: http://localhost:8091
 
* Autentique-se com as credenciais configuradas (Administrator / senha_secreta).

* Crie um novo Bucket chamado fitness_app.

* Acesse as configurações do Bucket em Scopes & Collections, entre no escopo _default e crie uma nova Collection chamada perfis.


### 3. Executando a API Backend (Spring Boot)
No terminal da sua IDE ou prompt de comando na pasta do projeto Java, execute o script do Maven para compilar e rodar a aplicação:

```bash
# No Windows PowerShell
.\mvnw spring-boot:run

# No Linux/macOS ou Prompt de Comando tradicional
mvnw spring-boot:run
```
O servidor inicializará com sucesso na porta 8080.

### 4. Acessando a Interface (Frontend)
Como a arquitetura é totalmente desacoplada, o frontend não depende de servidores adicionais. Basta abrir o arquivo index.html diretamente em seu navegador preferido para começar a interagir com o sistema.

---

### Parte 4: Endpoints, Tuning e Conclusão

## 📊 Endpoints da API REST

Abaixo estão listadas as rotas expostas pelo `PerfilUsuarioController`:

* **`POST /api/perfis`**
  * **Descrição:** Recebe os dados cadastrais básicos do aluno, processa a rotina semanal através do padrão *Strategy* e persiste o documento final no Couchbase.
  * **Status de Retorno:** `201 Created`
* **`GET /api/perfis/objetivo/{objetivo}`**
  * **Descrição:** Realiza uma busca profunda filtrando todos os alunos com base no objetivo selecionado.
  * **Status de Retorno:** `200 OK`

---

## 🔍 Otimização de Performance & Operações (Tuning)

### ⚡ Criação de Índice Secundário (N1QL)
Para impedir que o motor do Couchbase realize uma varredura completa na coleção (*Full Bucket Scan*) ao filtrar alunos por objetivo, foi implementado um Índice Secundário utilizando a linguagem declarativa N1QL.

Execute a instrução abaixo na aba **Query** do painel do Couchbase:

```sql
CREATE INDEX idx_objetivo ON fitness_app._default.perfis(objetivo);
```

---

## 💾 Backup Estruturado (Processo de Dump)
Para extrair todos os registros armazenados no banco no formato de documentos JSON legíveis (texto puro) para fins de auditoria ou backup,

* Gere o dump internamente no contêiner do banco de dados:
 ```bash
  docker exec -t couchbase-server cbexport json -c couchbase://127.0.0.1 -u Administrator -p senha_secreta -b fitness_app -f lines -o /tmp/meu_dump.json
  ```
* Copie o arquivo gerado para a sua máquina de desenvolvimento local:
```bash
  docker cp couchbase-server:/tmp/meu_dump.json ./meu_dump.json
   ```

---

## 📋 Demonstração Prática do CRUD
* O ciclo completo de persistência e manipulação dos dados pode ser validado diretamente através das duas abas principais da interface gráfica:

* Aba de Cadastro: Envia as informações do aluno e exibe instantaneamente a estrutura hierárquica do JSON gerado com os 5 dias de cronograma mapeados.

* Aba de Listagem: Realiza consultas diretas ao banco NoSQL utilizando o índice otimizado para agrupar e exibir os cartões dos alunos filtrados.
