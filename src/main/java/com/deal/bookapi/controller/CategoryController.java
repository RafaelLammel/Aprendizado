package com.deal.bookapi.controller;

import com.deal.bookapi.entity.Category;
import com.deal.bookapi.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/categorias")
    public ResponseEntity<List<Category>> getCategories(
            @RequestParam(required = false, defaultValue = "", name = "ordenarPor") String orderBy
    ) {
        return ResponseEntity.ok(categoryService.getCategories(orderBy));
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") int id) {
        Optional<Category> c = categoryService.getCategory(id);
        return c.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/categorias")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Optional<Category> c = Optional.ofNullable(categoryService.createCategory(category));
        if(c.isPresent()) {
            URI location = URI.create(String.format("/categorias/%s", c.get().getId()));
            return ResponseEntity.created(location).body(c.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/categorias/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category, @PathVariable("id") int id) {
        Optional<Category> c = Optional.ofNullable(categoryService.updateCategory(category, id));
        return c.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/categorias/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") int id) {
        if(categoryService.deleteCategory(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
