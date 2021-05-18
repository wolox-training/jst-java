package com.wolox.training.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

import com.wolox.training.controllers.vo.UsersVO;
import com.wolox.training.models.Users;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    private final static String URL = "/api/v1/users";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService mockUserService;

    private Users oneTestUser;

    @BeforeEach
    public void setUp() {
        oneTestUser = new Users("stella", "Joel Stella", LocalDate.of(1995, 10, 28), new ArrayList<>());
    }

    @Test
    public void whenCreateUser_thenReturnCreatedUser() throws Exception {
        Mockito.when(mockUserService.create(any(UsersVO.class))).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.post(URL)
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"userName\": \"stella\",\n"
                        + "    \"name\": \"Joel Stella\",\n"
                        + "    \"birthDate\": \"1995-10-28\",\n"
                        + "    \"books\": []\n"
                        + "}"));
    }

    @Test
    public void whenAddBook_thenReturnUser() throws Exception {
        Mockito.when(mockUserService.addUserBook(anyLong(), anyLong())).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.patch(URL.concat("/0/addBook"))
                .param("bookId", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"userName\": \"stella\",\n"
                        + "    \"name\": \"Joel Stella\",\n"
                        + "    \"birthDate\": \"1995-10-28\",\n"
                        + "    \"books\": []\n"
                        + "}"));
    }

    @Test
    public void whenRemoveBook_thenReturnUser() throws Exception {
        Mockito.when(mockUserService.removeUserBook(anyLong(), anyLong())).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.patch(URL.concat("/0/removeBook"))
                .param("bookId", "0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"userName\": \"stella\",\n"
                        + "    \"name\": \"Joel Stella\",\n"
                        + "    \"birthDate\": \"1995-10-28\",\n"
                        + "    \"books\": []\n"
                        + "}"));
    }

    @Test
    public void whenFindAllUsers_thenReturnAllUsers() throws Exception {
        Mockito.when(mockUserService.findAll()).thenReturn(Arrays.asList(oneTestUser));
        mvc.perform(MockMvcRequestBuilders.get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[{\n"
                        + "    \"id\": 0,\n"
                        + "    \"userName\": \"stella\",\n"
                        + "    \"name\": \"Joel Stella\",\n"
                        + "    \"birthDate\": \"1995-10-28\",\n"
                        + "    \"books\": []\n"
                        + "}]"));
    }

    @Test
    public void whenUpdateUser_thenReturnUpdatedUser() throws Exception {
        Mockito.when(mockUserService.updateUser(any(UsersVO.class), anyLong())).thenReturn(oneTestUser);
        mvc.perform(MockMvcRequestBuilders.put(URL.concat("/0"))
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\n"
                        + "    \"id\": 0,\n"
                        + "    \"userName\": \"stella\",\n"
                        + "    \"name\": \"Joel Stella\",\n"
                        + "    \"birthDate\": \"1995-10-28\",\n"
                        + "    \"books\": []\n"
                        + "}"));
    }

    @Test
    public void whenDeleteUser_thenDeleteUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(URL.concat("/0"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
