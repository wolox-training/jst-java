package com.wolox.training.services;

import com.wolox.training.controllers.vo.BookVO;
import com.wolox.training.exceptions.BookNotFoundException;
import com.wolox.training.models.Book;
import com.wolox.training.repositories.BookRepository;
import com.wolox.training.services.rest.OpenLibraryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OpenLibraryService openLibraryService;

    public Book create(BookVO bookVO) {
        return bookRepository.save(bookVO.toModel());
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByTitle(String bookTitle) {
        return bookRepository.findByTitle(bookTitle);
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    public Optional<Book> findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book findByIsbnExternalApi(String isbn) {
        Book book = openLibraryService.bookInfo(isbn);

        return bookRepository.save(book);
    }

    public Book updateBook(BookVO bookVO, Long id) {
        Book bookToUpdate = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

        bookToUpdate.update(bookVO.toModel());

        return bookRepository.save(bookToUpdate);
    }

    public void delete(Long id) {
        bookRepository.findById(id).orElseThrow(BookNotFoundException::new);

        bookRepository.deleteById(id);
    }
}
