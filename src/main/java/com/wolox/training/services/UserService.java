package com.wolox.training.services;

import com.wolox.training.controllers.vo.UsersVO;
import com.wolox.training.exceptions.UserNotFoundException;
import com.wolox.training.models.Book;
import com.wolox.training.models.Users;
import com.wolox.training.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Users create(UsersVO usersVO) {
        Users user = usersVO.toModel();
        user.setPassword(passwordEncoder.encode(usersVO.getPassword()));

        return userRepository.save(user);
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

    public Users updateUserPassword(Long userId, String password) {
        Users user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.setPassword(passwordEncoder.encode(password));

        return userRepository.save(user);
    }

    public Page<Users> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public Users updateUser(UsersVO usersVO, Long id) {
        Users usersToUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        usersToUpdate.update(usersVO.toModel());

        return userRepository.save(usersToUpdate);
    }

    public void delete(Long id) {
        userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.deleteById(id);
    }

    public boolean validateLogin(String userName, String password) {
        Users user = userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);

        return passwordEncoder.matches(password, user.getPassword());
    }
}
