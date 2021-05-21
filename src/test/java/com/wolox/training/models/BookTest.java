package com.wolox.training.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BookTest {

    private Book oneTestBook;

    @BeforeEach
    public void setUp() {
        oneTestBook = new Book("Terror", "Stephen King", "img.png", "It", "Worst clown ever", "Viking Press", "1986",
                1502, "4578-8665");
    }

    @Test
    public void whenSetGenre_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setGenre(null);
        });
    }

    @Test
    public void whenSetAuthor_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setAuthor(null);
        });
    }

    @Test
    public void whenSetImage_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setImage(null);
        });
    }

    @Test
    public void whenSetTitle_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setTitle(null);
        });
    }

    @Test
    public void whenSetSubtitle_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setSubtitle(null);
        });
    }

    @Test
    public void whenSetPublisher_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setPublisher(null);
        });
    }

    @Test
    public void whenSetYear_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setYear(null);
        });
    }

    @Test
    public void whenSetPages_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setPages(null);
        });
    }

    @Test
    public void whenSetIsbn_thenThrowException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            oneTestBook.setIsbn(null);
        });
    }
}
