package com.deal.bookapi.service;

import com.deal.bookapi.entity.Book;
import com.deal.bookapi.entity.Category;
import com.deal.bookapi.repository.BookRepository;
import com.deal.bookapi.repository.CategoryRepository;
import com.deal.bookapi.request.BookRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public List<Book> getBooks(String orderBy) {
        switch (orderBy) {
            case "preco":
                return bookRepository.findByOrderByPrice();
            case "paginas":
                return bookRepository.findByOrderByPages();
            default:
                return bookRepository.findAll();
        }
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
        log.error("Cadastro de livro - Categoria "+ bookRequest.getId_category() +" não existe");
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
            log.error("Modificação Livro - Categoria "+ bookRequest.getId_category() +" não existe");
            return null;
        }
        log.error("Modificação Livro - Livro não existe");
        return null;
    }

    public boolean deleteBook(int id) {
        if(bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            log.info("Deletar Livro - Livro " + id + " excluído com sucesso");
            return true;
        }
        log.error("Deletar Livro - Livro não existe");
        return false;
    }

}
