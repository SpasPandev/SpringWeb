package com.example.books.service.Impl;

import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.Book;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<BookDTO> getAllBooks() {

        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookDTO> getBookById(Long id) {

        return bookRepository.findById(id)
                .map(book -> modelMapper.map(book, BookDTO.class));
    }

    @Override
    public void deleteBookById(Long id) {

        bookRepository.deleteById(id);
    }
}
