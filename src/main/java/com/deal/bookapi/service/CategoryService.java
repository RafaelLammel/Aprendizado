package com.deal.bookapi.service;

import com.deal.bookapi.entity.Category;
import com.deal.bookapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(String orderBy) {
        if(orderBy.equals("nome")) 
            return categoryRepository.findByOrderByName();
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategory(int id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category;
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category, int id) {
        if (categoryRepository.existsById(id)) {
            category.setId(id);
            return categoryRepository.save(category);
        }
        return null;
    }

    public boolean deleteCategory(int id) {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
