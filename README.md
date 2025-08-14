# Challenge Fórum Hub 👥
API REST desenvolvida no Challenge Back End da Alura + Oracle Next Education para simular um fórum com múltiplos recursos e autenticação.

---
## Endpoints principais

- **CRUD completo** para usuários, tópicos, cursos e respostas (operando via REST)  
  - **POST**: criar  
  - **GET**: consultar/listar  
  - **PUT**: atualizar  
  - **DELETE**: deletar  

- **Perfis de usuário**: administrador e usuário comum  
  - Administradores podem criar, atualizar e deletar qualquer recurso  
  - Usuários comuns podem criar tópicos/respostas e gerenciar apenas seus próprios dados  

- **Autenticação via JWT** (necessária para todos os endpoints, exceto login e cadastro)  
- **Validações de entrada**  
- **Persistência em banco relacional**

---
## Tecnologias

- Java 17+  
- Spring Boot 
- Spring Data JPA
- Spring Security
- Banco relacional (MySql)
- JWT 
- Maven

---
## Configuração

Clone o repositório  
```bash
git clone https://github.com/cristiellesreis/challenge-forum_hub.git
cd challenge-forum_hub
```
## Configure o banco de dados

Configure o banco de dados no application.properties:
```properties
spring.datasource.url=${DATASOURCE_URL}
spring.datasource.username=${DATASOURCE_USERNAME}
spring.datasource.password=${DATASOURCE_PASSWORD}
```
## Executando a aplicação

Rode o comando:
```bash
./mvnw spring-boot:run
```
Acesse a API localmente em: [http://localhost:8080](http://localhost:8080)

## Autenticação

Para acessar endpoints protegidos, envie o JWT no cabeçalho:
```
Authorization: Bearer <SEU_TOKEN>
```

---
## 📚 Créditos
Este projeto foi proposto como desafio pelo programa:

- [Oracle Next Education](https://www.oracle.com/br/education/oracle-next-education/)
- [Alura - Cursos de tecnologia](https://www.alura.com.br/)

## 📄 Licença
Projeto de uso educacional, livre para estudos e modificações.

---
Desenvolvido com ☕ por Cristielle Reis
