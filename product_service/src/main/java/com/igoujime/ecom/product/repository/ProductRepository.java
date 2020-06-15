package com.igoujime.ecom.product.repository;

import com.igoujime.ecom.product.entity.Category;
import com.igoujime.ecom.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryOrderBySalesRankDesc(Category category);

}
