package com.igoujime.ecom.product.service.impl;

import com.igoujime.ecom.product.entity.Category;
import com.igoujime.ecom.product.repository.CategoryRepository;
import com.igoujime.ecom.product.service.CategoryService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        LOGGER.info("Save category: {}", category);
        Category saved = categoryRepository.save(category);
        return saved;
    }

    @Override
    public Category update(Integer id, Category updated) {
        LOGGER.info("Update category, id: {}, {}", id, updated);
        Category category = categoryRepository.findOne(id);
        category.setName(updated.getName());
        category.setDescription(updated.getDescription());
        Category saved = categoryRepository.save(category);
        return saved;
    }

    @Override
    public Category findOne(Integer id) {
        LOGGER.info("Find category, id: {}", id);
        return categoryRepository.findOne(id);
    }

    @Override
    public List<Category> findAll() {
        LOGGER.info("Find all categorys");
        Iterable<Category> categorys = categoryRepository.findAll();
        return IteratorUtils.toList(categorys.iterator());
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("Delete category, id: {}", id);
        categoryRepository.delete(id);
    }

}
