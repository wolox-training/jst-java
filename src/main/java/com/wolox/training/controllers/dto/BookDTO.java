package com.wolox.training.controllers.dto;

import com.wolox.training.models.Book;
import lombok.Getter;

@Getter
public class BookDTO {

    private String genre;

    private String author;

    private String image;

    private String title;

    private String subtitle;

    private String publisher;

    private String year;

    private Integer pages;

    private String isbn;

    public Book toModel() {
        return new Book(genre, author, image, title, subtitle, publisher, year, pages, isbn);
    }
}
