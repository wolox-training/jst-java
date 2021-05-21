package com.wolox.training.models;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.sun.istack.NotNull;
import com.wolox.training.exceptions.BookAlreadyOwnedException;
import com.wolox.training.utils.GuavaPreConditionsMessage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "Users from Wolox training")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotNull
    @ApiModelProperty(notes = "The user's username")
    private String userName;

    @Column(nullable = false)
    @NotNull
    @ApiModelProperty(notes = "The user's password")
    private String password;

    @Column(nullable = false)
    @NotNull
    @ApiModelProperty(notes = "The user's name")
    private String name;

    @Column(nullable = false)
    @NotNull
    @ApiModelProperty(notes = "The user's birthDate")
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
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userName),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "userName"));
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(password),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "password"));
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name),
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "name"));
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = Preconditions.checkNotNull(birthDate,
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "birthDate"));
    }

    public List<Book> getBooks() {
        return (List<Book>) Collections.unmodifiableList(books);
    }

    public void setBooks(List<Book> books) {
        this.books = Preconditions.checkNotNull(books,
                String.format(GuavaPreConditionsMessage.CHECK_NOT_NULL.getMessage(), "books"));
    }
}
