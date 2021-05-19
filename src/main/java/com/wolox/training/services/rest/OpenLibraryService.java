package com.wolox.training.services.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolox.training.exceptions.BookNotFoundException;
import com.wolox.training.exceptions.OpenLibraryJsonParseException;
import com.wolox.training.models.Book;
import com.wolox.training.services.rest.dto.BookDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenLibraryService {

    @Value("${open.library.base.url}")
    private String baseUrl;

    public Book bookInfo(String isbn) {
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(String.format(baseUrl, isbn), String.class);
        if (response.length() == 2) {
            throw new BookNotFoundException();
        }

        String responseBody = response
                .substring(response.indexOf("{", 1), response.length() - 1);
        BookDTO bookDTO = null;
        try {
            bookDTO = new ObjectMapper().readValue(responseBody, BookDTO.class);
            bookDTO.setIsbn(isbn);
        } catch (JsonProcessingException e) {
            throw new OpenLibraryJsonParseException();
        }

        return bookDTO.toModel();
    }
}
