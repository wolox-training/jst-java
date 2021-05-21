package com.wolox.training.models;

import com.sun.istack.NotNull;
import com.wolox.training.exceptions.BookAlreadyOwnedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull
    private String userName;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private LocalDate birthDate;

    @Column(nullable = false)
    @NotNull
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private List<Book> books = new ArrayList<>();

    public Users() {
    }

    public Users(String userName, String name, LocalDate birthDate, List<Book> books) {
        this.userName = userName;
        this.name = name;
        this.birthDate = birthDate;
        this.books = books;
    }

    public boolean addBook(Book book) {
        if (books.contains(book)) {
            throw new BookAlreadyOwnedException();
        }
        return books.add(book);
    }

    public boolean removeBook(Book book) {
        if (books.contains(book)) {
            return books.remove(book);
        }
        return false;
    }

    public void update(Users users) {
        this.userName = users.getUserName();
        this.name = users.getName();
        this.birthDate = users.getBirthDate();
        this.books = users.books;
    }

    public long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
