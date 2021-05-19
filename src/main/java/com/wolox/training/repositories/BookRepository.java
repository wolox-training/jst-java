package com.wolox.training.repositories;


import com.wolox.training.models.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Optional<Book> findByAuthor(String author);

    public List<Book> findByTitle(String bookTitle);

    public Optional<Book> findByIsbn(String isbn);

    public List<Book> findByPublisherAndGenreAndYear(String publisher, String genre, String year);

}
