package com.wolox.training.services;

import com.wolox.training.controllers.dto.UsersDTO;
import com.wolox.training.exceptions.UserNotFoundException;
import com.wolox.training.models.Book;
import com.wolox.training.models.Users;
import com.wolox.training.repositories.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    public Users create(UsersDTO usersDTO) {
        return userRepository.save(usersDTO.toModel());
    }

    public Users addUserBook(Long userId, Long bookId) {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Book book = bookService.findById(bookId);

        user.addBook(book);

        return userRepository.save(user);
    }

    public Users removeUserBook(Long userId, Long bookId) {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Book book = bookService.findById(bookId);

        user.removeBook(book);

        return userRepository.save(user);
    }

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users updateUser(UsersDTO usersDTO, Long id) {
        Users usersToUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        usersToUpdate.update(usersDTO.toModel());

        return userRepository.save(usersToUpdate);
    }

    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
    }

}
