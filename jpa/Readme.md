# Projeto Spring Data JPA 

## Descrição do Projeto

O projeto **Spring Data JPA | Curso 2024** é um aplicativo de exemplo desenvolvido para demonstrar o uso do Spring Data JPA em um ambiente real. O objetivo principal é criar um sistema de gerenciamento de livros com mapeamento objeto-relacional (ORM) utilizando anotações JPA. O projeto cobre uma variedade de conceitos e técnicas essenciais para o desenvolvimento de aplicações Java com Spring Data JPA.

## O que Foi Feito

### 1. Configuração do Projeto

- **Criação do Projeto:** Inicializamos o projeto Spring Boot e configuramos o Maven como ferramenta de gerenciamento de dependências.
- **Configuração do Banco de Dados:** Configuramos a conexão com uma base de dados PostgreSQL usando PgAdmin e definimos as propriedades de conexão no arquivo `application.properties`.

### 2. Mapeamento das Entidades JPA

- **Definição das Entidades:** Criamos as entidades principais do sistema, como `BookModel`, `AuthorModel`, `PublisherModel` e `ReviewModel`. Cada entidade é mapeada para uma tabela no banco de dados.
- **Anotações JPA:** Utilizamos anotações como `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@OneToMany`, `@ManyToOne`, `@ManyToMany`, e `@OneToOne` para definir a estrutura do banco de dados e os relacionamentos entre as entidades.

### 3. Implementação dos Relacionamentos

- **Relacionamentos OneToMany e ManyToOne:** Implementamos os relacionamentos entre `BookModel` e `AuthorModel`, bem como entre `BookModel` e `PublisherModel`.
- **Relacionamentos ManyToMany:** Configuramos os relacionamentos muitos-para-muitos para associar livros a autores.
- **Relacionamentos OneToOne:** Estabelecemos o relacionamento um-para-um entre `BookModel` e `ReviewModel`.

### 4. Repositórios e Serviços

- **Repositórios:** Criamos interfaces de repositório para as entidades usando `JpaRepository`, o que nos permite realizar operações CRUD de forma fácil e eficiente.
- **Serviços:** Implementamos a lógica de negócios no serviço `BookService`, incluindo métodos para salvar, encontrar e deletar livros. Usamos transações para garantir a integridade dos dados.

### 5. Controladores

- **Controlador REST:** Desenvolvemos o controlador `BookController` para expor endpoints RESTful para operações CRUD relacionadas a livros. Utilizamos `@PostMapping` para criar novos livros, `@GetMapping` para listar todos os livros, e métodos adicionais para operações específicas.

### 6. Testes e Validações

- **Testes:** Realizamos testes manuais usando Postman para validar os endpoints e garantir que as operações CRUD estejam funcionando corretamente.
- **Validações:** Implementamos validações básicas para garantir que os dados inseridos estejam corretos e completos.

## Preparação do Ambiente

Para executar este projeto, você precisará das seguintes ferramentas:

- **JDK 21:** Ambiente de desenvolvimento Java.
- **Maven:** Gerenciamento de dependências e construção do projeto.
- **Postman:** Ferramenta para testar as APIs REST.
- **PgAdmin (Postgres):** Gerenciamento do banco de dados PostgreSQL.
- **IDE (IntelliJ, STS, Eclipse, etc):** Ambiente de desenvolvimento integrado para codificação.

## Links Úteis

- **Projeto no GitHub:** [Acesse o repositório completo](https://github.com/MichelliBrito/spri...)

## Conclusão

Este projeto oferece uma visão abrangente sobre o uso do Spring Data JPA para mapear entidades e gerenciar relacionamentos em um banco de dados. Ele é uma excelente base para aprender e aplicar conceitos de ORM e JPA em projetos Java.




# Explicação do Código `BookModel`

Esse código representa uma classe de modelo chamada `BookModel`, usada para mapear uma entidade do banco de dados em uma aplicação Java com JPA (Java Persistence API). Abaixo está uma explicação detalhada de cada parte do código:

## 1. Pacote e Importações

package com.bookstore.jpa.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

Pacote: com.bookstore.jpa.models organiza a classe dentro da estrutura do projeto.

Importações:
jakarta.persistence.* inclui as anotações e classes necessárias para trabalhar com JPA.
java.io.Serializable é uma interface que permite que objetos da classe BookModel sejam serializados.
java.util.UUID é uma classe que representa identificadores únicos universais (UUIDs), usados aqui como o tipo do identificador da entidade.

2. Anotações de Entidade e Tabela

@Entity
@Table(name = "TB_BOOK")

@Entity: Indica que a classe BookModel é uma entidade JPA, ou seja, representa uma tabela no banco de dados.

@Table(name = "TB_BOOK"): Especifica o nome da tabela no banco de dados à qual essa entidade está mapeada (TB_BOOK).

3. Implementação de Serializable

public class BookModel implements Serializable {
    private static final long serialVersionUID = 1L;
    
implements Serializable: Permite que objetos dessa classe sejam convertidos em um fluxo de bytes, o que é necessário para persistência, cache ou transmissão de dados.

serialVersionUID: Um identificador exclusivo que é usado durante o processo de serialização para garantir que uma versão deserializada seja compatível.

4. Campo ID
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID id;
@Id: Indica que o campo id é a chave primária da entidade.

@GeneratedValue(strategy = GenerationType.AUTO): Especifica que o valor do id será gerado automaticamente pelo sistema usando a estratégia AUTO, que deixa a JPA decidir a melhor estratégia de geração (geralmente baseada no banco de dados subjacente).

UUID id: Usa UUID como o tipo de dado para a chave primária, garantindo um identificador único para cada instância da entidade.

5. Campo Title
@Column(nullable = false, unique = true)
private String title;

@Column(nullable = false, unique = true): Configurações da coluna:

nullable = false: O campo title não pode ser nulo, ou seja, é obrigatório.
unique = true: Garante que o valor do title seja único no banco de dados, não permitindo duplicatas.

6. Métodos Getter e Setter

public UUID getId() {
    return id;
}

public void setId(UUID id) {
    this.id = id;
}

public String getTitle() {
    return title;
}

public void setTitle(String title) {
    this.title = title;
}

Métodos getId() e setId(UUID id): Usados para acessar e modificar o valor do campo id.

Métodos getTitle() e setTitle(String title): Usados para acessar e modificar o valor do campo title.

Resumo
Essa classe BookModel representa um modelo de livro no sistema, onde cada livro tem um id único (UUID) e um title que deve ser obrigatório e único no banco de dados. O mapeamento é feito utilizando anotações JPA, que gerenciam como essa classe interage com o banco de dados relacional.

## Relacionamento das Tabelas

No código apresentado, o relacionamento entre as entidades `PublisherModel` e `BookModel` é definido usando anotações JPA. A seguir, detalhamos como esse relacionamento funciona:

### @OneToMany

@OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
private Set<BookModel> books = new HashSet<>();


### Relacionamento: 
A anotação @OneToMany indica que a entidade PublisherModel (editora) tem um relacionamento de um para muitos com a entidade BookModel (livro). Isso significa que uma única editora pode estar associada a vários livros.

mappedBy: O atributo mappedBy = "publisher" especifica o nome do atributo na classe BookModel que é responsável por esse relacionamento. Neste caso, o atributo publisher na entidade BookModel mapeia a relação de muitos livros para uma editora.

fetch = FetchType.LAZY: O carregamento do relacionamento é definido como "preguiçoso" (lazy). Isso significa que a coleção de livros (books) associados à editora só será carregada da base de dados quando for acessada pela primeira vez, não durante a consulta inicial da entidade PublisherModel. Isso pode melhorar o desempenho, especialmente quando a coleção é grande e nem sempre necessária.

Coleção de Livros: A propriedade private Set<BookModel> books é usada para armazenar a coleção de livros associados à editora. Um Set foi utilizado para garantir que não haja duplicatas, e o HashSet é uma implementação específica do Set.

Resumo
O relacionamento definido indica que uma editora (PublisherModel) pode ter muitos livros (BookModel), e a relação é mapeada através do campo publisher na entidade BookModel. O carregamento "preguiçoso" (FetchType.LAZY) otimiza a performance ao não carregar a coleção de livros até que ela seja explicitamente acessada.