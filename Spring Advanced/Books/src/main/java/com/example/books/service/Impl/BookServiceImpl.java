package com.example.books.service.Impl;

import com.example.books.model.dto.AuthorDTO;
import com.example.books.model.dto.BookDTO;
import com.example.books.model.entity.Author;
import com.example.books.model.entity.Book;
import com.example.books.repository.AuthorRepository;
import com.example.books.repository.BookRepository;
import com.example.books.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Long updateBook(BookDTO bookDTO) {

        Book book = bookRepository.findById(bookDTO.getId()).orElse(null);

        if (book == null) {
            return null;
        }

        Author author = authorRepository.findByName(bookDTO.getAuthor().getName())
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(bookDTO.getAuthor().getName());

                    return authorRepository.save(newAuthor);
                });

        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(author);

        return bookRepository.save(book).getId();
    }

    @Override
    public Page<BookDTO> getBooks(Integer pageNo, Integer pageSize, String sortBy) {

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return bookRepository
                .findAll(pageable)
                .map(this::asBook);
    }

    private BookDTO asBook (Book book) {

        BookDTO bookDTO = modelMapper.map(book, BookDTO.class);
        AuthorDTO authorDTO = modelMapper.map(book.getAuthor(), AuthorDTO.class);

        bookDTO.setAuthor(authorDTO);

        return bookDTO;
    }

    private Author createNewAuthor(String authorName) {

        Author newAuthor = new Author();

        newAuthor.setName(authorName);

        return authorRepository.save(newAuthor);
    }
}
