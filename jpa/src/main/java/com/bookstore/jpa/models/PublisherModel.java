package com.bookstore.jpa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "TB_PUBLISHER")
public class PublisherModel implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;

    //Definindo relacionamento das tabelas
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)

    //Uma editora tem muitos livros
    @OneToMany(mappedBy = "publisher", fetch = FetchType.LAZY)
    // FetchType.LAZY: Define que a associação será carregada de forma preguiçosa (lazy), ou seja, os dados da entidade relacionada
    // (no caso, "publisher") só serão carregados quando acessados pela primeira vez, não durante a consulta inicial da entidade principal.

    //Definindo a coleção de livros que a editora tem:
    private Set<BookModel> books = new HashSet<>();


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookModel> getBooks() {
        return books;
    }

    public void setBooks(Set<BookModel> books) {
        this.books = books;
    }
}
