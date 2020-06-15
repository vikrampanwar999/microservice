package com.igoujime.ecom.cart.service.impl;

import com.igoujime.ecom.cart.entity.Product;
import com.igoujime.ecom.cart.mapper.JsonMapperWrapper;
import com.igoujime.ecom.cart.repository.ProductRepository;
import com.igoujime.ecom.cart.service.ProductService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    private final JsonMapperWrapper jsonMapper;


    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,
                              JsonMapperWrapper jsonMapper) {
        this.productRepository = productRepository;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Product save(Product product) {
        LOGGER.info("Save product: {}", product);
        Product saved = productRepository.save(product);
        return saved;
    }

    @Override
    public Product update(Integer id, Product updated) {
        LOGGER.info("Update product, id: {}, {}", id, updated);
        Product product = productRepository.findOne(id);
        product.setName(updated.getName());
        product.setDescription(updated.getDescription());
        product.setImageUrl(updated.getImageUrl());
        product.setPrice(updated.getPrice());
        Product saved = productRepository.save(product);
        return saved;
    }

    @Override
    public Product findOne(Integer id) {
        LOGGER.info("Find product, id: {}", id);
        return productRepository.findOne(id);
    }

    @Override
    public List<Product> findAll() {
        LOGGER.info("Find all products");
        Iterable<Product> products = productRepository.findAll();
        return IteratorUtils.toList(products.iterator());
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("Delete product, id: {}", id);
        productRepository.delete(id);
    }

}
