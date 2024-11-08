# NEKI-SistemaSkill_Backend_Part2

Este projeto é uma API RESTful desenvolvida em Spring Boot para gerenciar usuários e suas habilidades (skills). A API fornece funcionalidades de login, cadastro de usuários, e gerenciamento de skills, protegidas por JWT (JSON Web Token). A documentação dos serviços é gerada automaticamente pelo Swagger.

## Funcionalidades

1. **Serviço de Login**
   - **Descrição:** Verifica as credenciais do usuário (login e senha) e retorna um token JWT.
   - **Endpoints:**
     - `POST /auth/signin`
   - **Detalhes:**
     - A senha é verificada após ser criptografada.
     - Retorna um token JWT para acesso aos demais serviços.
     - Retorna o ID do usuário.

2. **Serviço de Cadastro**
   - **Descrição:** Permite que um novo usuário seja registrado na base de dados.
   - **Endpoints:**
     - `POST /users/signup`
   - **Detalhes:**
     - A senha é armazenada de forma criptografada.

3. **Serviço de Listagem de Usuários**
   - **Descrição:** Lista todas os usuários cadastradas no banco de dados.
   - **Endpoints:**
     - `GET /users`
   - **Segurança:** Protegido por JWT.

4. **Serviço de Listagem de Usuário por ID**
   - **Descrição:** Lista um único usuário capturado pelo ID com seu username e sua lista de skills.
   - **Endpoints:**
     - `GET /users/{id}`
   - **Segurança:** Protegido por JWT.

5. **Serviço de Listagem de Skills**
   - **Descrição:** Lista todas as skills cadastradas no banco de dados de forma paginada, ordenada e com opção de filtro.
   - **Endpoints:**
     - `GET /skills`
   - **Segurança:** Protegido por JWT.

6. **Serviço de criar Skill e associar ao usuário**
   - **Descrição:** Cria uma skill e a associa ao usuário que a criou. Neste serviço ao criar já pode alterar o nível da skil.
   - **Endpoints:**
     - `POST /skills/create-add`
   - **Segurança:** Protegido por JWT.

7. **Serviço de Associar Skill**
   - **Descrição:** Associa uma skill a um usuário com seu nível inicialmente em 1 para que o usuário possa modificar em sua lista de skills.
   - **Endpoints:**
     - `POST /skills/add-existing`
   - **Segurança:** Protegido por JWT.

8. **Serviço de Listar Associação de Skill**
   - **Descrição:** Lista todas as skills associadas ao usuário de forma paginada, ordenada e com opção de filtro.
   - **Endpoints:**
     - `GET /user-skills`
   - **Segurança:** Protegido por JWT.

8. **Serviço de Atualizar Associação de Skill**
   - **Descrição:** Atualiza o nível de uma skill associada a um usuário.
   - **Endpoints:**
     - `PUT /user-skills/level`
   - **Segurança:** Protegido por JWT.

9. **Serviço de Excluir Associação de Skill**
   - **Descrição:** Remove a associação de uma skill de um usuário.
   - **Endpoints:**
     - `DELETE /userskills/{id}`
   - **Segurança:** Protegido por JWT.

## Segurança

- O serviço de login é público.
- Todos os demais serviços são protegidos por JWT. Um token válido deve ser fornecido no cabeçalho das requisições para acessar esses serviços.

## Documentação

- A documentação da API é gerada automaticamente e pode ser acessada no Swagger UI.
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html#/`

## Tecnologias Utilizadas

- **Spring Boot:** Framework principal para o desenvolvimento da API.
- **Spring Security:** Para a implementação da segurança com JWT.
- **Spring Data JPA:** Para a persistência dos dados.
- **JWT (JSON Web Token):** Para autenticação e autorização.
- **BCrypt:** Para a criptografia das senhas.

## Como Executar o Projeto

1. **Pré-requisitos:** Certifique-se de ter o [Java JDK 17](https://www.oracle.com/br/java/technologies/downloads/#java21) e o [Maven](https://maven.apache.org/download.cgi) instalados.

2. **Clonar o Repositório:**
   ```bash
   git clonehttps://github.com/RaphaelDamico/NEKI-SistemaSkill_Backend_Part2.git

