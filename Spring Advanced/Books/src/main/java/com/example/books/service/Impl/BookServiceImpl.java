package com.example.books.service.Impl;

import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.Author;
import com.example.books.model.entity.Book;
import com.example.books.repository.AuthorRepository;
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
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
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

    @Override
    public Long createBook(BookDTO bookDTO) {

        Optional<Author> author = authorRepository.findByName(bookDTO.getAuthor().getName());


        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(author.orElseGet(() -> createNewAuthor(bookDTO.getAuthor().getName())));

        return bookRepository.save(book).getId();
    }

    private Author createNewAuthor(String authorName) {

        Author newAuthor = new Author();

        newAuthor.setName(authorName);

        return authorRepository.save(newAuthor);
    }
}
