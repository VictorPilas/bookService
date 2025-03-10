package com.example.bookService.model;

import java.text.SimpleDateFormat;

public class BookResponse {
    private Book book;
    private String date;

    public BookResponse(Book book) {
        this.book = book;
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        this.date = book.getPublicationDate() != null ? sdf.format(book.getPublicationDate()) : "No Date";
    }

    // Getters
    public Book getBook() { return book; }
    public String getDate() { return date; }
}
