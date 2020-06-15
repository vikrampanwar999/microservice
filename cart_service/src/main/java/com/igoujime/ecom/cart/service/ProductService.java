package com.igoujime.ecom.cart.service;


import com.igoujime.ecom.cart.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product update(Integer id, Product product);

    Product findOne(Integer id);

    List<Product> findAll();

    void delete(Integer id);

}
