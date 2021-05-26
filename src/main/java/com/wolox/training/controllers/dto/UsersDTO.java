package com.wolox.training.controllers.dto;

import com.wolox.training.models.Book;
import com.wolox.training.models.Users;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class UsersDTO {

    private String userName;

    private String password;

    private String name;

    private LocalDate birthDate;

    private List<Book> books = new ArrayList<>();

    public Users toModel() {
        return new Users(userName, name, birthDate, books);
    }
}
