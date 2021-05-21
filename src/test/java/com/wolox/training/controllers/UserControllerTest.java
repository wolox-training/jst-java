package com.wolox.training.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.wolox.training.controllers.dto.UsersDTO;
import com.wolox.training.models.Users;
import com.wolox.training.security.CustomAuthenticationProvider;
import com.wolox.training.services.UserService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String URL = "/api/v1/users";
    private static final String JSON_RESPONSE_ONE_USER = "{\n"
            + "    \"id\": 0,\n"
            + "    \"userName\": \"stella\",\n"
            + "    \"name\": \"Joel Stella\",\n"
            + "    \"birthDate\": \"1995-10-28\",\n"
            + "    \"books\": []\n"
            + "}";
    private static final String JSON_RESPONSE_PAGE_USER = "{\"content\":[{\n"
            + "    \"id\": 0,\n"
            + "    \"userName\": \"stella\",\n"
            + "    \"name\": \"Joel Stella\",\n"
            + "    \"birthDate\": \"1995-10-28\",\n"
            + "    \"books\": []\n"
            + "}]}";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService mockUserService;

    @MockBean
    private CustomAuthenticationProvider authProvider;

    private Users oneTestUser;

    @BeforeEach
    public void setUp() {
        oneTestUser = new Users("stella", "Joel Stella", LocalDate.of(1995, 10, 28), new ArrayList<>());
    }

    @Test
    public void whenCreateUser_thenReturnCreatedUser() throws Exception {
        Mockito.when(mockUserService.create(any(UsersDTO.class))).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.post(URL)
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_ONE_USER));
    }

    @Test
    @WithMockUser
    public void whenAddBook_thenReturnUser() throws Exception {
        Mockito.when(mockUserService.addUserBook(anyLong(), anyLong())).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.patch(URL.concat("/0/addBook"))
                .param("bookId", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_ONE_USER));
    }

    @Test
    @WithMockUser
    public void whenRemoveBook_thenReturnUser() throws Exception {
        Mockito.when(mockUserService.removeUserBook(anyLong(), anyLong())).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.patch(URL.concat("/0/removeBook"))
                .param("bookId", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_ONE_USER));
    }

    @Test
    @WithMockUser
    public void whenFindAllUsers_thenReturnAllUsers() throws Exception {
        Mockito.when(mockUserService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(oneTestUser)));
        mvc.perform(MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_PAGE_USER));
    }

    @Test
    @WithMockUser
    public void whenUpdateUser_thenReturnUpdatedUser() throws Exception {
        Mockito.when(mockUserService.updateUser(any(UsersDTO.class), anyLong())).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.put(URL.concat("/0"))
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSON_RESPONSE_ONE_USER));
    }

    @Test
    @WithMockUser
    public void whenDeleteUser_thenDeleteUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(URL.concat("/0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
