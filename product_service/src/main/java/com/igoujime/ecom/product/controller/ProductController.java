package com.igoujime.ecom.product.controller;

import com.igoujime.ecom.product.entity.Product;
import com.igoujime.ecom.product.service.CategoryService;
import com.igoujime.ecom.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/")
    public List<Product> getAll() {
        return productService.findAll();
    }

    @GetMapping(value = "/category/{id}")
    public List<Product> getAllByCategory(@PathVariable Integer id) {
        return productService.findByCategory(categoryService.findOne(id));
    }

    @GetMapping(value = "/{id}")
    public Product getOne(@PathVariable Integer id) {
        return productService.findOne(id);
    }

    @PostMapping(value = "/")
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping(value = "/{id}")
    public Product update(@PathVariable Integer id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

}
