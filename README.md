# URL Shortener

O URL Shortener é um projeto que visa criar um serviço de encurtamento de URLs. O objetivo é criar um serviço que permita aos usuários encurtar URLs longas e compartilhá-las facilmente.

## Características

* **Encurtamento de URLs**: O serviço permite que os usuários encurtem URLs longas e as transformem em URLs curtas e fáceis de compartilhar.
* **Redirecionamento de URLs**: O serviço redireciona os usuários para a URL original quando eles acessam a URL curta.
* **Expiração de URLs**: O serviço permite que as URLs curtas sejam excluídas automaticamente após um tempo parametrizado.
## Tecnologias Utilizadas

* **Java**: A linguagem de programação utilizada para desenvolver o projeto.
* **Spring Boot**: O framework utilizado para criar a aplicação web.
* **MongoDB**: O banco de dados utilizado para armazenar as URLs.
* **Testcontainers**: Para testar integração com MongoDB
## Como Utilizar

**Pré-requisitos**

* Ter o Docker instalado na máquina.

**Iniciar a Aplicação**

1. Clone o repositório do projeto.
2. Execute o comando `docker-compose up` para iniciar a aplicação.
3. Acesse a URL `http://localhost:8080` para utilizar o serviço.

**Variáveis de Ambiente**


* `SERVER-PORT`: A porta do servidor. Valor padrão: `8080`.
* `DB_USERNAME`: O nome de usuário do banco de dados. Valor padrão: `root`.
* `DB_PASSWORD`: A senha do banco de dados.
* `DB_DATABASE`: O nome do banco de dados.
* `DB_PASSWORD`: A senha do banco de dados.
*
* `DB_HOST`: O host do banco de dados. Valor padrão: `localhost`.
* `DB_PORT`: A porta do banco de dados. Valor padrão: `27017`.
* `DB_NAME`
* 
**Exemplos de Chamadas**

* **Encurtar URL**:
+ Método: `POST`
+ URL: `http://localhost:8080/shorten`
+ Corpo da requisição: `{"url": "https://www.example.com"}`
+ Resposta: `{"shortUrl": "http://localhost:8080/abc123"}`
* **Redirecionar URL**:
+ Método: `GET`
+ URL: `http://localhost:8080/abc123`
+ Resposta: Redirecionamento para a URL original (`https://www.example.com`)

## Contribuição

Se você deseja contribuir para o projeto, por favor, faça um fork do repositório e envie um pull request com suas alterações.

: O nome do banco de dados. Valor padrão: `url_shortener`.
