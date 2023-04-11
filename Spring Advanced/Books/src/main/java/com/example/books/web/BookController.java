package com.example.books.web;

import com.example.books.model.dto.BookDTO;
import com.example.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {

        Optional<BookDTO> book = bookService.getBookById(id);

        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(book.get());
        }
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {

        bookService.deleteBookById(id);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping("/books")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO,
                                              UriComponentsBuilder uriComponentsBuilder) {

        Long bookId = bookService.createBook(bookDTO);

        URI location = uriComponentsBuilder
                .path("/books/{id}")
                .buildAndExpand(bookId)
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @PutMapping("books/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id,
                                          @RequestBody BookDTO bookDTO) {

        bookDTO.setId(id);

        Long updateBookId = bookService.updateBook(bookDTO);

        return updateBookId == null ?
                ResponseEntity.notFound().build()
                : ResponseEntity.noContent().build();
    }

}
