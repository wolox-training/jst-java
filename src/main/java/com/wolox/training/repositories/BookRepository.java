package com.wolox.training.repositories;


import com.wolox.training.models.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * @param author author of the book to find
     * @return the book found by author or empty optional
     */
    public Optional<Book> findByAuthor(String author);

    public List<Book> findByTitle(String bookTitle);

}
