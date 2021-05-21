package com.wolox.training.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.wolox.training.controllers.dto.BookDTO;
import com.wolox.training.models.Book;
import com.wolox.training.security.CustomAuthenticationProvider;
import com.wolox.training.services.BookService;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    private static final String URL = "/api/v1/books";
    private static final String JSON_RESPONSE_ONE_BOOK = "{\n"
            + "    \"id\": 0,\n"
            + "    \"genre\": \"Terror\",\n"
            + "    \"author\": \"Stephen King\",\n"
            + "    \"image\": \"img.png\",\n"
            + "    \"title\": \"It\",\n"
            + "    \"subtitle\": \"Worst clown ever\",\n"
            + "    \"publisher\": \"Viking Press\",\n"
            + "    \"year\": \"1986\",\n"
            + "    \"pages\": 1502,\n"
            + "    \"isbn\": \"4578-8665\"\n"
            + "}";
    private static final String JSON_RESPONSE_LIST_BOOK = "[{\n"
            + "    \"id\": 0,\n"
            + "    \"genre\": \"Terror\",\n"
            + "    \"author\": \"Stephen King\",\n"
            + "    \"image\": \"img.png\",\n"
            + "    \"title\": \"It\",\n"
            + "    \"subtitle\": \"Worst clown ever\",\n"
            + "    \"publisher\": \"Viking Press\",\n"
            + "    \"year\": \"1986\",\n"
            + "    \"pages\": 1502,\n"
            + "    \"isbn\": \"4578-8665\"\n"
            + "}]";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService mockBookService;

    @MockBean
    private CustomAuthenticationProvider authProvider;

    private Book oneTestBook;

    @BeforeEach
    public void setUp() {
        oneTestBook = new Book("Terror", "Stephen King", "img.png", "It", "Worst clown ever", "Viking Press", "1986",
                1502, "4578-8665");
    }

    @Test
    public void whenCreateBook_thenReturnCreatedBook() throws Exception {
        Mockito.when(mockBookService.create(any(BookDTO.class))).thenReturn(oneTestBook);
        mvc.perform(MockMvcRequestBuilders.post(URL)
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_ONE_BOOK));
    }

    @Test
    @WithMockUser
    public void whenFindAllBooks_thenReturnAllBooks() throws Exception {
        Mockito.when(mockBookService.findAll()).thenReturn(Arrays.asList(oneTestBook));
        mvc.perform(MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_LIST_BOOK));
    }

    @Test
    @WithMockUser
    public void whenFindByTitle_thenReturnBooksByTitle() throws Exception {
        Mockito.when(mockBookService.findByTitle("It")).thenReturn(Arrays.asList(oneTestBook));
        mvc.perform(MockMvcRequestBuilders.get(URL.concat("/title/It"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_LIST_BOOK));
    }

    @Test
    @WithMockUser
    public void whenFindById_thenReturnFoundBook() throws Exception {
        Mockito.when(mockBookService.findById(0L)).thenReturn(oneTestBook);
        mvc.perform(MockMvcRequestBuilders.get(URL.concat("/0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_ONE_BOOK));
    }

    @Test
    @WithMockUser
    public void whenUpdateBook_thenReturnUpdatedBook() throws Exception {
        Mockito.when(mockBookService.updateBook(any(BookDTO.class), anyLong())).thenReturn(oneTestBook);
        mvc.perform(MockMvcRequestBuilders.put(URL.concat("/0"))
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_ONE_BOOK));
    }

    @Test
    @WithMockUser
    public void whenDeleteBook_thenRemoveBook() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(URL.concat("/0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
