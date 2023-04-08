package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.Book;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> getAllBooks() {

        List<BookDTO> allBooks = bookService.getAllBooks();

        return ResponseEntity.ok(allBooks);
    }
}
