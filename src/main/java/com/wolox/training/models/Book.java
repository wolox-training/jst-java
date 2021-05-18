package com.wolox.training.models;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.sun.istack.NotNull;
import com.wolox.training.utils.GuavaPreConditionsMessage;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = true)
    private String genre;

    @Column(nullable = false)
    @NotNull
    private String author;

    @Column(nullable = false)
    @NotNull
    private String image;

    @Column(nullable = false)
    @NotNull
    private String title;

    @Column(nullable = false)
    @NotNull
    private String subtitle;

    @Column(nullable = false)
    @NotNull
    private String publisher;

    @Column(nullable = false)
    @NotNull
    private String year;

    @Column(nullable = false)
    @NotNull
    private Integer pages;

    @Column(nullable = false, unique = true)
    @NotNull
    private String isbn;

    @ManyToMany(mappedBy = "books")
    private List<Users> users = new ArrayList<>();

    public Book() {
    }

    public Book(String genre, String author, String image, String title, String subtitle, String publisher,
            String year, Integer pages, String isbn) {
        this.genre = genre;
        this.author = author;
        this.image = image;
        this.title = title;
        this.subtitle = subtitle;
        this.publisher = publisher;
        this.year = year;
        this.pages = pages;
        this.isbn = isbn;
    }

    /**
     * @param book the book for obtains the params to update
     */
    public void update(Book book) {
        this.genre = book.getGenre();
        this.author = book.getAuthor();
        this.image = book.getImage();
        this.title = book.getTitle();
        this.subtitle = book.getSubtitle();
        this.publisher = book.getPublisher();
        this.year = book.getYear();
        this.pages = book.getPages();
        this.isbn = book.getIsbn();
    }

    public long getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(genre),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "genre"));
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(author),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "author"));
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(image),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "image"));
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(title),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "title"));
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(subtitle),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "subtitle"));
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(publisher),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "publisher"));
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(year),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "year"));
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = Preconditions.checkNotNull(pages,
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "pages"));
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(isbn),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "isbn"));
        this.isbn = isbn;
    }
}
