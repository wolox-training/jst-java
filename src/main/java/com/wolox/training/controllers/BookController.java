package com.wolox.training.controllers;

import com.wolox.training.controllers.dto.BookDTO;
import com.wolox.training.models.Book;
import com.wolox.training.services.BookService;
import com.wolox.training.services.rest.OpenLibraryService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/api/v1/books")
public class BookController {

    private static final String NAME = "name";
    private static final String GREETING_VIEW = "greeting";

    @Autowired
    private BookService bookService;

    @Autowired
    private OpenLibraryService openLibraryService;

    @GetMapping("/greeting")
    /**
     * @param name the name for show in the view
     * @param model the {@link Model} to add attributes for show in the view
     * @return the view to show the greet
     */
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute(NAME, name);
        return GREETING_VIEW;
    }

    @PostMapping
    /**
     * @param bookVO the {@link BookVO} with the attributes that can choose the user to create it
     * @return the created {@link Book} with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<Book> create(@RequestBody BookDTO bookDTO) {
        return new ResponseEntity<>(bookService.create(bookDTO), HttpStatus.CREATED);
    }

    @GetMapping
    /**
     * @return a list of books filtering by parameters
     */
    public @ResponseBody
    ResponseEntity<Page<Book>> findAll(
            @RequestParam(required = false, defaultValue = "") String genre,
            @RequestParam(required = false, defaultValue = "") String author,
            @RequestParam(required = false, defaultValue = "") String image,
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String subtitle,
            @RequestParam(required = false, defaultValue = "") String publisher,
            @RequestParam(required = false, defaultValue = "") String year,
            @RequestParam(required = false, defaultValue = "0") Integer pages,
            @RequestParam(required = false, defaultValue = "") String isbn,
            Pageable pageable) {
        return new ResponseEntity<>(
                bookService.findAllBooks(genre, author, image, title, subtitle, publisher, year, pages, isbn, pageable),
                HttpStatus.OK);
    }

    @GetMapping("/title/{bookTitle}")
    /**
     * @param bookTitle the title of the {@link Book} to find
     * @return the list of found {@link Book} with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<Page<Book>> findByTitle(@PathVariable String bookTitle, Pageable pageable) {
        return new ResponseEntity<>(bookService.findByTitle(bookTitle, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    /**
     * @param id the id of the {@link Book} to find
     * @return the found {@link Book} with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<Book> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> findByIsbn(@PathVariable String isbn) {
        Optional<Book> book = bookService.findByIsbn(isbn);

        if (!book.isPresent()) {
            return new ResponseEntity<>(bookService.findByIsbnExternalApi(isbn), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(book.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    /**
     * @param bookVO the {@link BookVO} with the attributes that the user can change
     * @param id the id with the {@link Book} to update
     * @return the updated {@link Book} with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<Book> updateBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        return new ResponseEntity<>(bookService.updateBook(bookDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * @param id the id of the {@link Book} to delete
     */
    public @ResponseBody
    void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
