package com.wolox.training.repositories;


import com.wolox.training.models.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * @param author author of the book to find
     * @return the book found by author or empty optional
     */
    Optional<Book> findByAuthor(String author);

    Page<Book> findByTitle(String bookTitle, Pageable pageable);

    Optional<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b WHERE (:publisher IS NULL OR b.publisher = :publisher) "
            + "and (:genre IS NULL OR b.genre = :genre) "
            + "and (:year IS NULL OR b.year = :year)")
    List<Book> findByPublisherAndGenreAndYear(@Param("publisher") String publisher,
            @Param("genre") String genre, @Param("year") String year);

    @Query("SELECT b FROM Book b WHERE (:genre = '' OR b.genre = :genre)"
            + "and (:author = '' OR b.author = :author)"
            + "and (:image = '' OR b.image = :image)"
            + "and (:title = '' OR b.title = :title)"
            + "and (:subtitle = '' OR b.subtitle = :subtitle)"
            + "and (:publisher = '' OR b.publisher = :publisher)"
            + "and (:year = '' OR b.year = :year)"
            + "and (:pages = 0 OR b.pages = :pages)"
            + "and (:isbn = '' OR b.isbn = :isbn)")
    Page<Book> findAllBooks(@Param("genre") String genre, @Param("author") String author,
            @Param("image") String image,
            @Param("title") String title, @Param("subtitle") String subtitle,
            @Param("publisher") String publisher, @Param("year") String year, @Param("pages") Integer pages,
            @Param("isbn") String isbn, Pageable pageable);
}
