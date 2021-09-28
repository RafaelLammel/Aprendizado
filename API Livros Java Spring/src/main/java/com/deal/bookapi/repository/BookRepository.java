package com.deal.bookapi.repository;

import java.util.List;

import com.deal.bookapi.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByOrderByPrice();
    List<Book> findByOrderByPages();

}
