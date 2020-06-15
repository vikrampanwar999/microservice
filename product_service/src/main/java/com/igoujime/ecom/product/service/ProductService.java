package com.igoujime.ecom.product.service;

import com.igoujime.ecom.product.entity.Category;
import com.igoujime.ecom.product.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product update(Integer id, Product product);

    Product findOne(Integer id);

    List<Product> findAll();

    List<Product> findByCategory(Category category);

    void delete(Integer id);

}
