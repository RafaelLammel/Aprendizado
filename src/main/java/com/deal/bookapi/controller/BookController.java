package com.deal.bookapi.controller;

import com.deal.bookapi.entity.Book;
import com.deal.bookapi.request.BookRequest;
import com.deal.bookapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/livros")
    public ResponseEntity<List<Book>> getBooks(
            @RequestParam(required = false, defaultValue = "", name = "ordenarPor") String orderBy
    ) {
        return ResponseEntity.ok(bookService.getBooks(orderBy));
    }

    @GetMapping("/livros/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Optional<Book> b = bookService.getBook(id);
        return b.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/livros")
    public ResponseEntity<Book> createBook(@RequestBody BookRequest bookRequest) {
        Optional<Book> b = Optional.ofNullable(bookService.createBook(bookRequest));
        if(b.isPresent()) {
            URI location = URI.create(String.format("/livros/%s", b.get().getId()));
            return ResponseEntity.created(location).body(b.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/livros/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody BookRequest bookRequest, @PathVariable("id") int id) {
        Optional<Book> b = Optional.ofNullable(bookService.updateBook(bookRequest, id));
        return b.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/livros/{id}")
    public ResponseEntity deleteBook(@PathVariable("id") int id) {
        if(bookService.deleteBook(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
