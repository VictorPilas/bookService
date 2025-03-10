package com.example.bookService.controller;

import com.example.bookService.model.BookResponse;
import com.example.bookService.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/filter")
    public Optional<BookResponse> filterBooks(@RequestParam String filter) {
        return bookService.filter(filter);
    }
}
