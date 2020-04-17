package com.deal.bookapi.service;

import com.deal.bookapi.entity.Book;
import com.deal.bookapi.entity.Category;
import com.deal.bookapi.repository.BookRepository;
import com.deal.bookapi.repository.CategoryRepository;
import com.deal.bookapi.request.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> getBooks(String orderBy) {
        if(orderBy.equals("preco")) {
            return bookRepository.findAll().stream()
                    .sorted(Comparator.comparing(Book::getPrice))
                    .collect(Collectors.toList());
        }
        if(orderBy.equals("paginas")) {
            return bookRepository.findAll().stream()
                    .sorted(Comparator.comparing(Book::getPages))
                    .collect(Collectors.toList());
        }
        return bookRepository.findAll();
    }

    public Optional<Book> getBook(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book;
    }

    public Book createBook(BookRequest bookRequest) {
        Optional<Category> category = categoryRepository.findById(bookRequest.getId_category());
        if(category.isPresent()) {
            Book b = new Book(bookRequest);
            b.setCategory(category.get());
            return bookRepository.save(b);
        }
        return null;
    }

    public Book updateBook(BookRequest bookRequest, int id) {
        if (bookRepository.existsById(id)) {
            Optional<Category> category = categoryRepository.findById(bookRequest.getId_category());
            if(category.isPresent()) {
                Book b = new Book(bookRequest);
                b.setCategory(category.get());
                b.setId(id);
                return bookRepository.save(b);
            }
            return null;
        }
        return null;
    }

    public boolean deleteBook(int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
