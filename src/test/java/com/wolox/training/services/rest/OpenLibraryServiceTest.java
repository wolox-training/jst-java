package com.wolox.training.services.rest;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.wolox.training.exceptions.BookNotFoundException;
import com.wolox.training.models.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import(value = OpenLibraryService.class)
@TestPropertySource("classpath:test.application.properties")
public class OpenLibraryServiceTest {

    private static final String ISBN = "0385472579";
    private static final String URL = String.format("/api/books?bibkeys=ISBN:%s&format=json&jscmd=data", ISBN);

    private WireMockServer wireMockServer;

    @Autowired
    private OpenLibraryService openLibraryService;

    @BeforeEach
    public void setUp() {
        wireMockServer = new WireMockServer(8082, 8083);
        this.wireMockServer.start();
    }

    @AfterEach
    public void resetWiremock() {
        this.wireMockServer.stop();
    }

    @Test
    public void whenFindBookByIsbn_thenReturnBook() {
        this.wireMockServer.stubFor(
                WireMock.get(URL)
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("open-library-response.json"))
        );

        Book book = openLibraryService.bookInfo(ISBN);

        assertEquals(book.getIsbn(), ISBN);
    }

    @Test
    public void whenFindBookByIsbn_thenThrowException() {
        this.wireMockServer.stubFor(
                WireMock.get(URL)
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody("{}"))
        );

        assertThrows(BookNotFoundException.class, () -> openLibraryService.bookInfo(
                ISBN));
    }
}
