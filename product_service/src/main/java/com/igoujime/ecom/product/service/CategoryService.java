package com.igoujime.ecom.product.service;


import com.igoujime.ecom.product.entity.Category;

import java.util.List;

public interface CategoryService {

    Category save(Category category);

    Category update(Integer id, Category category);

    Category findOne(Integer id);

    List<Category> findAll();

    void delete(Integer id);

}
