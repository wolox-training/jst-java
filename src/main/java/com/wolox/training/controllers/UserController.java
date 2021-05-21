package com.wolox.training.controllers;

import com.wolox.training.controllers.dto.UsersDTO;
import com.wolox.training.models.Users;
import com.wolox.training.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ApiOperation(value = "Create an user", response = Users.class)
    @ApiResponses(value = @ApiResponse(code = 201, message = "User created"))
    public @ResponseBody
    ResponseEntity<Users> create(@RequestBody UsersDTO usersDTO) {
        return new ResponseEntity<>(userService.create(usersDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/addBook")
    @ApiOperation(value = "Add user's book", response = Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added book"),
            @ApiResponse(code = 400, message = "User or book to add not found")
    })
    public ResponseEntity<Users> addBook(@PathVariable(name = "id") Long userId,
            @ApiParam(value = "Book's id to add", required = true) @RequestParam Long bookId) {
        return new ResponseEntity<>(userService.addUserBook(userId, bookId), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/removeBook")
    @ApiOperation(value = "Remove user's book", response = Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Removed book"),
            @ApiResponse(code = 400, message = "User or book to remove not found")
    })
    public ResponseEntity<Users> removeBook(@PathVariable(name = "id") Long userId,
            @ApiParam(value = "Book's id to remove", required = true) @RequestParam Long bookId) {
        return new ResponseEntity<>(userService.removeUserBook(userId, bookId), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Find all users", response = Users.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successfully users found"))
    public @ResponseBody
    ResponseEntity<List<Users>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an user", response = Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully user updated"),
            @ApiResponse(code = 400, message = "User not found")
    })
    public @ResponseBody
    ResponseEntity<Users> updateBook(@RequestBody UsersDTO usersDTO, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(usersDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete an user")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Successfully user deleted"),
            @ApiResponse(code = 400, message = "User not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
