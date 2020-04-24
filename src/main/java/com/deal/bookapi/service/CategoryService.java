package com.deal.bookapi.service;

import com.deal.bookapi.entity.Category;
import com.deal.bookapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Page<Category> getCategories(String orderBy, String page, String pageSize) {
        return categoryRepository.findAll(PageRequest.of(
                Integer.parseInt(page),
                Integer.parseInt(pageSize),
                orderBy.equals("nome") ? Sort.by("name") : Sort.unsorted()
                )
        );
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
