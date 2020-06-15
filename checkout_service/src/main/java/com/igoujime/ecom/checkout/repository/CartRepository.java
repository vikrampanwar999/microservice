package com.igoujime.ecom.checkout.repository;


import com.igoujime.ecom.checkout.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
