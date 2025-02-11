# IDP - Programação Orientada a Objetos - Prova P2

## Descrição do Trabalho
Este projeto foi desenvolvido como parte da Prova P2 da disciplina de Programação Orientada a Objetos. O objetivo era criar um sistema orientado a objetos que utilizasse as seguintes tecnologias e abordagens:

1. **Banco de Dados Relacional com JDBC**
2. **Conceitos de Orientação a Objetos**
3. **Tratamento de Erros**
4. **CRUD** (Create, Read, Update, Delete) para entidades especificadas
5. **Autenticação e Listagem Personalizada**
6. **Interatividade com o Usuário via Menu (ou Testes Unitários)**

### Especificação
O sistema gerencia **usuários** e **grupos**. As funcionalidades incluem:

- **Usuário:**
  - Criar, listar, atualizar e excluir usuários
  - Autenticar um usuário por nome e senha

- **Grupo:**
  - Criar, listar, atualizar e excluir grupos
  - Listar os grupos aos quais um usuário específico pertence

### Requisitos Implementados
O projeto segue rigorosamente as especificações, com as seguintes implementações:

- **Orientação a Objetos:**
  - Separação clara de responsabilidades (DAO, modelos e lógica de negócios).

- **Banco de Dados:**
  - Conexão com o banco H2 utilizando a API JDBC.
  - Inicialização automática do banco de dados e tabelas.

- **Interatividade:**
  - Sistema interativo baseado em menu via `Scanner`.

- **Tratamento de Erros:**
  - Captura e exibição de mensagens de erro amigáveis para o usuário.

- **Dados Predefinidos:**
  - Usuários e grupos hardcoded para facilitar os testes.

---

## Estrutura do Projeto
Abaixo está a estrutura do projeto no GitHub:

```
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── org.example.main
│   │   │   │   └── Main.java       # Menu principal interativo
│   │   │   ├── org.example.dao
│   │   │   │   ├── UsuarioDAO.java # Operações CRUD para usuários
│   │   │   │   └── GrupoDAO.java   # Operações CRUD para grupos
│   │   │   ├── org.example.model
│   │   │   │   ├── Usuario.java    # Modelo de dados para Usuário
│   │   │   │   └── Grupo.java      # Modelo de dados para Grupo
│   │   │   └── org.example.util
│   │   │       ├── DatabaseConnection.java  # Configuração do JDBC
│   │   │       └── DatabaseInitializer.java # Inicialização do banco de dados
│   ├── test
│       └── java
│           └── org.example.dao
│               ├── UsuarioDAOTest.java # Testes para UsuarioDAO
│               └── GrupoDAOTest.java   # Testes para GrupoDAO
├── pom.xml     # Configuração do Maven e dependências
└── README.md   # Documentação do projeto
```

---

## Como Rodar o Projeto
Siga os passos abaixo para configurar e executar o sistema:

### Pré-requisitos
1. **Java 17+**: Certifique-se de que o Java está instalado em sua máquina.
2. **Maven**: Necessário para gerenciar dependências e compilar o projeto.

### Configuração do Projeto
1. Clone o repositório do GitHub:
   ```bash
   git clone https://github.com/Felipebc2/IDP_POO_P2.git
   ```
2. Navegue até a pasta do projeto:
   ```bash
   cd IDP_POO_P2
   ```
3. Compile o projeto usando Maven:
   ```bash
   mvn clean install
   ```

### Executando o Sistema
1. Execute a classe principal com o seguinte comando:
   ```bash
   mvn exec:java -Dexec.mainClass="org.example.main.Main"
   ```
2. O menu interativo será exibido no terminal. Escolha uma das opções para interagir com o sistema.

---

## Funcionalidades do Menu

### Opções Disponíveis

1. **Criar Usuário:** Permite adicionar um novo usuário ao sistema.
2. **Listar Usuários:** Exibe todos os usuários cadastrados.
3. **Atualizar Usuário:** Altere informações de um usuário existente.
4. **Excluir Usuário:** Remove um usuário do sistema.
5. **Criar Grupo:** Adicione um novo grupo com seus respectivos membros.
6. **Listar Grupos:** Mostra todos os grupos cadastrados, incluindo seus membros.
7. **Atualizar Grupo:** Modifique as informações de um grupo.
8. **Excluir Grupo:** Apaga um grupo do banco de dados.
9. **Autenticar Usuário:** Verifica se as credenciais fornecidas estão corretas.
10. **Listar Grupos de um Usuário:** Exibe todos os grupos aos quais um usuário específico pertence.
0. **Sair:** Finaliza o sistema.

---

## Testes
O projeto inclui testes unitários e de integração, localizados na pasta `src/test/java`. Para executá-los:

```bash
mvn test
```

Os resultados dos testes serão exibidos no terminal, e um relatório será gerado em `target/surefire-reports`.

---
