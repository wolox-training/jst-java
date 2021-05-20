package com.wolox.training.controllers;

import com.wolox.training.controllers.vo.UsersVO;
import com.wolox.training.models.Users;
import com.wolox.training.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/loggedUser")
    @ApiOperation(value = "Logged user", response = Users.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successfully user"))
    public ResponseEntity<Users> currentUser(Principal principal) {
        Users user = new Users();
        user.setUserName(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Create an user", response = Users.class)
    @ApiResponses(value = @ApiResponse(code = 201, message = "User created"))
    public @ResponseBody
    ResponseEntity<Users> create(@RequestBody UsersVO usersVO) {
        return new ResponseEntity<>(userService.create(usersVO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/addBook")
    @ApiOperation(value = "Add user's book", response = Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Added book"),
            @ApiResponse(code = 400, message = "User or book to add not found")
    })
    public ResponseEntity<Users> addBook(@PathVariable(name = "id") Long userId,
            @ApiParam(value = "Book's id to add", required = true) @RequestParam Long bookId) {
        Users user = userService.addUserBook(userId, bookId);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/removeBook")
    @ApiOperation(value = "Remove user's book", response = Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Removed book"),
            @ApiResponse(code = 400, message = "User or book to remove not found")
    })
    public ResponseEntity<Users> removeBook(@PathVariable(name = "id") Long userId,
            @ApiParam(value = "Book's id to remove", required = true) @RequestParam Long bookId) {
        Users user = userService.removeUserBook(userId, bookId);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/password")
    @ApiOperation(value = "Update user's password", response = Users.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Password updated successfully"),
            @ApiResponse(code = 400, message = "User to update not found")})
    public ResponseEntity<Users> updateUserPassword(@PathVariable Long id,
            @RequestParam(name = "password") String password) {
        Users user = userService.updateUserPassword(id, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    @ApiOperation(value = "Find all users", response = Users.class)
    @ApiResponses(value = @ApiResponse(code = 200, message = "Successfully users found"))
    public @ResponseBody
    ResponseEntity<Page<Users>> findAll(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update an user", response = Users.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully user updated"),
            @ApiResponse(code = 400, message = "User not found")
    })
    public @ResponseBody
    ResponseEntity<Users> updateBook(@RequestBody UsersVO usersVO, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUser(usersVO, id), HttpStatus.OK);
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
