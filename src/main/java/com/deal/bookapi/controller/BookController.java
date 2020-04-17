package com.deal.bookapi.controller;

import com.deal.bookapi.entity.Book;
import com.deal.bookapi.entity.Category;
import com.deal.bookapi.repository.BookRepository;
import com.deal.bookapi.repository.CategoryRepository;
import com.deal.bookapi.request.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping("/livros")
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/livros")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        Optional<Category> category = categoryRepository.findById(bookRequest.getId_category());
        if(category.isPresent()) {
            Book b = new Book(bookRequest);
            b.setCategory(category.get());
            URI location = URI.create(String.format("/livros/%s", b.getId()));
            return ResponseEntity.created(location).body(bookRepository.save(b));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody BookRequest bookRequest, @PathVariable("id") int id) {
        if (bookRepository.existsById(id)) {
            Optional<Category> category = categoryRepository.findById(bookRequest.getId_category());
            if(category.isPresent()) {
                Book b = new Book(bookRequest);
                b.setCategory(category.get());
                b.setId(id);
                return ResponseEntity.ok(bookRepository.save(b));
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
