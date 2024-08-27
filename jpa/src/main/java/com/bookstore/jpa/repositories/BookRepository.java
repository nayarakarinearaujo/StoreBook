package com.bookstore.jpa.repositories;

import com.bookstore.jpa.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<BookModel, UUID> {

    //Quando precisa de consulta por title
    BookModel findBookModelByTitle(String title);

    //QUando precisa de consulta por query
    @Query(value = "SELECT * FROM tb_book WHERE publisher_id = :id", nativeQuery = true)
    List<BookModel> findBookPublisherId(@Param("id") UUID id);
}