package com.wolox.training.repositories;


import com.wolox.training.models.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findByAuthor(String author);

    public List<Book> findByTitle(String bookTitle);

    public Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE (:publisher IS NULL OR b.publisher = :publisher) "
            + "and (:genre IS NULL OR b.genre = :genre) "
            + "and (:year IS NULL OR b.year = :year)")
    public List<Book> findByPublisherAndGenreAndYear(@Param("publisher") String publisher,
            @Param("genre") String genre, @Param("year") String year);

}
