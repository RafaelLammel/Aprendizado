package com.deal.bookapi.repository;

import java.util.List;

import com.deal.bookapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    List<Category> findByOrderByName();

}
