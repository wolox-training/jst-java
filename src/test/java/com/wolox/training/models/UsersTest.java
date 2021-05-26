package com.wolox.training.models;

import com.wolox.training.exceptions.BookAlreadyOwnedException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsersTest {

    private Book oneTestBook;
    private Users oneTestUser;

    @BeforeEach
    public void setUp() {
        oneTestBook = new Book("Terror", "Stephen King", "img.png", "It", "Worst clown ever", "Viking Press", "1986",
                1502, "4578-8665");
        oneTestUser = new Users("stella", "Joel Stella", LocalDate.of(1995, 10, 28), new ArrayList<>());
    }

    @Test
    public void whenAddBook_thenThrowException() {
        oneTestUser.addBook(oneTestBook);
        Assertions.assertThrows(BookAlreadyOwnedException.class, () -> {
            oneTestUser.addBook(oneTestBook);
        });
    }

    @Test
    public void whenRemoveBook_theReturnFalse() {
        Assertions.assertFalse(oneTestUser.removeBook(oneTestBook));
    }

    @Test
    public void whenSetUserName_thenThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            oneTestUser.setUserName(null);
        });
    }

    @Test
    public void whenSetName_thenThrowException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            oneTestUser.setName(null);
        });
    }

    @Test
    public void whenSetBirthDate_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestUser.setBirthDate(null);
        });
    }

    @Test
    public void whenSetBooks_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestUser.setBooks(null);
        });
    }
}
