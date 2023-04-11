package com.example.books.service;

import com.example.books.model.dto.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<BookDTO> getAllBooks();

    Optional<BookDTO> getBookById(Long id);

    void deleteBookById(Long id);

    Long createBook(BookDTO bookDTO);

    Long updateBook(BookDTO bookDTO);
}
