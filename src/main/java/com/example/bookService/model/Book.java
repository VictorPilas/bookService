package com.example.bookService.model;

import java.util.Date;

public class Book {
    private int id;
    private String title;
    private int pages;
    private String summary;
    private Author author;
    private Long publicationTimestamp;

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getPages() { return pages; }
    public String getSummary() { return summary; }
    public Author getAuthor() { return author; }
    public Long getPublicationTimestamp() { return publicationTimestamp; }
    public Date getPublicationDate() {
        return publicationTimestamp != null ? new Date(publicationTimestamp * 1000) : null;
    }
}
