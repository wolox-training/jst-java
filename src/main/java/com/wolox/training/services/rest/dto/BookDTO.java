package com.wolox.training.services.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.wolox.training.models.Book;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {

    private String isbn;

    private String title;

    private String subtitle;

    private List<PublisherDTO> publishers = new ArrayList<>();

    @JsonProperty("publish_date")
    private String publishDate;

    @JsonProperty("number_of_pages")
    private Integer numberOfPages;

    @JsonProperty
    private List<AuthorDTO> authors = new ArrayList<>();

    public Book toModel() {
        return new Book("undefined", authors.toString(), "undefined", title, subtitle, publishers.toString(),
                publishDate, numberOfPages, isbn);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<PublisherDTO> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<PublisherDTO> publishers) {
        this.publishers = publishers;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(Integer numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public List<AuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDTO> authors) {
        this.authors = authors;
    }
}
