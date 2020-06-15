package com.igoujime.ecom.product.controller;

import com.igoujime.ecom.product.entity.Category;
import com.igoujime.ecom.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/")
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Category getOne(@PathVariable Integer id) {
        return categoryService.findOne(id);
    }

    @PostMapping(value = "/")
    public Category create(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping(value = "/{id}")
    public Category update(@PathVariable Integer id, @RequestBody Category category) {
        return categoryService.update(id, category);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        categoryService.delete(id);
    }

}
