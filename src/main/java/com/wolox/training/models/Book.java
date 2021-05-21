package com.wolox.training.models;


import com.sun.istack.NotNull;
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
     * @param book the {@link Book} for obtains the params to update
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
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
