# Documentação do Projeto

## Visão Geral

Este projeto consiste em uma API Rest desenvolvida com **Spring Boot** para gerenciar clientes e ocorrências, incluindo armazenamento de evidências no **Min.io**. A solução implementa autenticação via JWT e suporta paginação, ordenação e filtros nos endpoints de listagem.

## Melhorias

- A tabela `Customer` e `Occurrence` deve ter um status para realizar uma exclusão lógica, para ter um histórico de dados.

## Tecnologias Utilizadas

- **Java**: 21
- **Spring Boot**: 3.4.3
- **Banco de Dados**: PostgreSQL
- **ORM**: JPA/Hibernate
- **Migrations**: Flyway
- **Autenticação**: JWT (Java JWT - Auth0)
- **Armazenamento de Arquivos**: Min.io
- **Segurança**: Spring Security
- **Gerenciamento de Dependências**: Maven
- **Containerização**: Docker/Docker Compose

# Documentação do Projeto

## Melhorias
1 - Tabela `Customer` e `Occurrence` deve ter um status para uma exclusão lógica.

## Requisitos Gerais
```
1. [x] Implementar mecanismo de autorização e autenticação
2. [x] A solução de autenticação deverá expirar a cada 30 minutos
3. [x] Conter recursos de paginação em todas as consultas
4. [x] Os dados produzidos deverão ser armazenados no servidor de banco de dados previamente criado em container
5. [x] Armazenar arquivos e imagens no servidor de armazenamento de objetos Min.io (Storage) previamente criado em container
6. [ ] Orquestrar a solução final utilizando Docker Compose de modo que inclua todos os contêineres utilizados (Servidor Min.io (Storage), Banco de Dados, Artefato Java)
7. [x] Utilizar Flyway Migrations para criar e popular tabelas do banco de dados
```

## Requisitos Específicos
```
Implementar uma API Rest para o diagrama de banco de dados acima tomando por base as seguintes orientações:

1. [x] Criar um CRUD para Cliente, Ocorrência, Endereço onde deverá ser contemplado a inclusão e edição dos dados das tabelas relacionadas.
2. [x] Criar um endpoint para realizar cadastro de ocorrências onde deverão ser enviadas as informações de endereço da ocorrência, nome e CPF do cliente envolvido junto à evidência em imagem.
3. [ ] Criar um endpoint para listar ocorrências apresentando também dados do cliente envolvido, endereço da ocorrência e link para acesso a evidência em imagem previamente cadastrada e disponibilizada pelo storage. O endpoint deverá permitir filtros por nome do cliente, CPF, data da ocorrência e cidade da ocorrência. Deve ser possível ordenar os registros pela data da ocorrência e cidade de forma ascendente ou decrescente.
4. [x] Criar um endpoint para finalização da ocorrência. Uma vez finalizada, a ocorrência não poderá ser alterada.
5. [x] Realizar o upload de uma ou mais imagens de evidências enviando-as para o Min.io.
```
## Configuração do Ambiente

### Variáveis de Ambiente

As seguintes variáveis podem ser configuradas:

```properties
SERVER_PORT

# Banco de Dados
APP_DB_URL
APP_DB_USERNAME
APP_DB_PASSWORD

# Segurança
API_SECURITY_TOKEN_SECRET

# Min.io
MINIO_URL
MINIO_ACCESS_KEY
MINIO_SECRET_KEY
MINIO_BUCKET_NAME

# Swagger
ENABLE_SWAGGER

```

## Estrutura do Projeto

```
/src/main/java/br/com/carbigdata/
    ├── controller/  # Controllers da API
    ├── service/     # Regras de negócio
    ├── repository/  # Camada de acesso ao banco de dados
    ├── domain/      # Entidades JPA
    ├── common/      # Configurações gerais (segurança, storage, etc)
    ├── utils/       # classes utilitarias 
    ├── shared/      # classes que podem ser compartilhada com outros services 
    ├── ENUM/        # Enumeradores utilizado no projeto
```

## Como Rodar o Projeto
```
1 - Clone o repositório
2 - Execute o comando `docker-compose up -d` para subir o banco de dados, minio e a aplicação
```

---
