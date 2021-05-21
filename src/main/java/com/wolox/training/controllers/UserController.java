package com.wolox.training.controllers;

import com.wolox.training.controllers.dto.UsersDTO;
import com.wolox.training.models.Users;
import com.wolox.training.services.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public @ResponseBody
    ResponseEntity<Users> create(@RequestBody UsersDTO usersDTO) {
        return new ResponseEntity<>(userService.create(usersDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/addBook")
    public ResponseEntity<Users> addBook(@PathVariable(name = "id") Long userId, @RequestParam Long bookId) {
        return new ResponseEntity<>(userService.addUserBook(userId, bookId), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/removeBook")
    public ResponseEntity<Users> removeBook(@PathVariable(name = "id") Long userId, @RequestParam Long bookId) {
        return new ResponseEntity<>(userService.removeUserBook(userId, bookId), HttpStatus.CREATED);
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<Users>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Users> updateBook(@RequestBody UsersDTO usersDTO, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(usersDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
