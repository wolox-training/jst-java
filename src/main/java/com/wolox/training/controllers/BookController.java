package com.wolox.training.controllers;

import com.wolox.training.controllers.vo.BookVO;
import com.wolox.training.models.Book;
import com.wolox.training.services.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/greeting")
    /**
     * @param name the name for show in the view
     * @param model the model to add attributes for show in the view
     * @return the view to show the greet
     */
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model) {
        model.addAttribute(NAME, name);
        return GREETING_VIEW;
    }

    @PostMapping
    /**
     * @param bookVO the book with the attributes that can choose the user to create it
     * @return the created book with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<Book> create(@RequestBody BookVO bookVO) {
        return new ResponseEntity<>(bookService.create(bookVO), HttpStatus.CREATED);
    }

    @GetMapping
    /**
     * @return a list of all books
     */
    public @ResponseBody
    ResponseEntity<List<Book>> findAll() {
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/title/{bookTitle}")
    /**
     * @param bookTitle the tiltle of the book to find
     * @return the list of found books with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<List<Book>> findByTitle(@PathVariable String bookTitle) {
        return new ResponseEntity<>(bookService.findByTitle(bookTitle), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    /**
     * @param id the id of the book to find
     * @return the found book with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<Book> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    /**
     * @param bookVO the book with the attributes that the user can change
     * @param id the id with the book to update
     * @return the updated book with the HTTP status
     */
    public @ResponseBody
    ResponseEntity<Book> updateBook(@RequestBody BookVO bookVO, @PathVariable Long id) {
        return new ResponseEntity<>(bookService.updateBook(bookVO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    /**
     * @param id the id of the book to delete
     */
    public @ResponseBody
    void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
