# Mercado do livro

Estudo de desenvolvimento de aplicações de API na linguagem **_Kotlin_** com framework **_Springboot_**.

### Objetivo
O Objetivo do projeto é criar uma aplicação que faça cadastro básicos de usuários e livros, utilizando alguns recursos do
Spring no kotlin.

### Executar a aplicação
  * JDK 11
  * MySql Local.
  * Postman
  * Cadastrar um perfil via post, executar o login e copiar o JWT 

### Swagger-ui
Acessar a portal http://localhost:8080/swagger-ui.html#/

### Customer

[POST] - /v1/customers

Tem como objetivo cadastrar o usuários na base de dados.

| Parametro | Tipo Parametro | Tipo de dado | Obrigatório | Descrição |
|:---:|:---:|:---:|:---:|:---:|
|Content-Type | header | String | Não | -- |

#### Status Code

| Código | Descrição |
|:---:|:---:|
| 201 | Created |

#### Request

* header

    ```
    Content-Type: application/json
    ```
* body

    ``` 
     {
       "name": "nome",
        "email": "nome@email.com",
        "password": "123456"
     }
    ```
* response

[GET] - /v1/customers

Tem como objetivo de exibir todos os customers cadastrados.

| Parametro | Tipo Parametro | Tipo de dado | Obrigatório | Descrição |
|:---:|:---:|:---:|:---:|:---:|
| |  |  |  |  |

#### Status Code

| Código | Descrição |
|:---:|:---:|
| 200 | Requisição executada com sucesso |

#### Request

* header

  ```
    Content-Type: application/json
  ```

* body


* response
  ```
    [
      {
        "id": 1,
        "name": "name",
        "email": "nome@email.com",
        "status": "ACTIVE"
      },
      {
        "id": 2,
        "name": "nome 2",
        "email": "nome2@email.com",
        "status": "ACTIVE"
      }
    ]
  ```
[GET] - /v1/customers/id

Tem como objetivo de exibir através da busca do ID do customer.

| Parametro | Tipo Parametro | Tipo de dado | Obrigatório | Descrição |
|:---:|:---:|:---:|:---:|:---:|
| |  |  |  |  |

#### Status Code

| Código | Descrição |
|:---:|:---:|
| 200 | Requisição executada com sucesso |

#### Request

* header

  ```
    Content-Type: application/json
  ```

* body


* response
  ```
      {
        "id": 1,
        "name": "name",
        "email": "nome@email.com",
        "status": "ACTIVE"
      }
  ```
[DELETE] - /v1/customers/id

Tem como objetivo mudar o status de ativo para inativo através do id do customer, dado que o usuário talvés ative 
a conta de novo.

| Parametro | Tipo Parametro | Tipo de dado | Obrigatório | Descrição |
|:---:|:---:|:---:|:---:|:---:|
| |  |  |  |  |

#### Status Code

| Código | Descrição |
|:---:|:---:|
| 204 | No Content |

#### Request

* header

  ```
    Content-Type: application/json
  ```

* body


* response
  ```
  ```
  

#### Glossário
* [Kotlin](https://kotlinlang.org/docs/home.html)
* [SpringSecurity](https://spring.io/guides/gs/securing-web/)
* [Boas práticas de API](https://tipscode.com.br/como-construir-uma-api-restful-conheca-as-13-melhores-boas-praticas)
* [Montar Readme](https://github.com/adam-p/markdown-here/wiki/Markdown-Cheatsheet)
* [Swegger](https://www.treinaweb.com.br/blog/documentando-uma-api-spring-boot-com-o-swagger)
