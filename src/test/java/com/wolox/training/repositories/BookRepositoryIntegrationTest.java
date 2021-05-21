package com.wolox.training.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.wolox.training.models.Book;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void whenFindByAuthor_thenReturnBook() {
        Book book = new Book("Terror", "Stephen King", "img.png", "It", "Worst clown ever", "Viking Press", "1986",
                1502, "4578-8665");
        entityManager.persist(book);

        Optional<Book> foundBook = bookRepository.findByAuthor("Stephen King");

        assertThat(foundBook.get().getIsbn()).isEqualTo("4578-8665");
    }

    @Test
    public void whenFindByTitle_thenReturnBooks() {
        Book book1 = new Book("Terror", "Stephen King", "img.png", "It", "Parte 1", "Viking Press", "1986",
                1502, "4578-8665");
        Book book2 = new Book("Terror", "Stephen King", "img.png", "It", "Parte 2", "Viking Press", "1986",
                1502, "4578-8666");
        entityManager.persist(book1);
        entityManager.persist(book2);

        List<Book> books = bookRepository.findByTitle("It");

        assertThat(books.size()).isEqualTo(2);
    }

    @Test
    public void whenFindByPublisherAndGenreAndYear_thenReturnBooks() {
        Book book1 = new Book("Terror", "Stephen King", "img.png", "It", "Parte 1", "Viking Press", "1986",
                1502, "4578-8665");
        Book book2 = new Book("Terror", "Stephen King", "img.png", "It", "Parte 2", "Viking Press", "1987",
                1502, "4578-8666");
        entityManager.persist(book1);
        entityManager.persist(book2);

        List<Book> books = bookRepository.findByPublisherAndGenreAndYear("Viking Press", "Terror", "1986");

        assertThat(books.size()).isEqualTo(1);
    }

    @Test
    public void whenFindByPublisherAndGenreAndYearWithNullParams_thenReturnBooks() {
        Book book1 = new Book("Terror", "Stephen King", "img.png", "It", "Parte 1", "Viking Press", "1986",
                1502, "4578-8665");
        Book book2 = new Book("Terror", "Stephen King", "img.png", "It", "Parte 2", "Viking Press", "1987",
                1502, "4578-8666");
        entityManager.persist(book1);
        entityManager.persist(book2);

        List<Book> books = bookRepository.findByPublisherAndGenreAndYear(null, null, "1986");

        assertThat(books.size()).isEqualTo(1);
    }
}
