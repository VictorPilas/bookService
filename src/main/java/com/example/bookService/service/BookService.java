package com.example.bookService.service;

import com.example.bookService.model.Book;
import com.example.bookService.model.BookResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private List<Book> books;

    @PostConstruct
    public void loadBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            books = objectMapper.readValue(new URL("file:src/main/resources/books.json"), new TypeReference<List<Book>>() {});
        } catch (Exception e) {
            books = new ArrayList<>();
        }
    }

    public Optional<BookResponse> filter(String filter) {
        books.stream()
            .filter(book -> book.getPublicationTimestamp() == null)
            .forEach(book -> System.out.println("Libro sin fecha: " + book.getTitle()));

        List<Book> filteredBooks = books.stream()
            .filter(book -> book.getTitle().toLowerCase().contains(filter.toLowerCase()) ||
                    book.getSummary().toLowerCase().contains(filter.toLowerCase()) ||
                    book.getAuthor().getBio().toLowerCase().contains(filter.toLowerCase()))
            .collect(Collectors.toList());

        if (filteredBooks.isEmpty()) return Optional.empty();

        Book latestBook = filteredBooks.stream()
            .filter(book -> book.getPublicationTimestamp() != null)
            .max(Comparator.comparing(Book::getPublicationTimestamp))
            .orElse(filteredBooks.get(0));

        books.sort(Comparator
            .comparing((Book book) -> book.getPublicationTimestamp() == null ? Long.MIN_VALUE : book.getPublicationTimestamp())
            .reversed()
            .thenComparing(book -> book.getAuthor().getBio().length()));

        return Optional.of(new BookResponse(latestBook));
    }
}
