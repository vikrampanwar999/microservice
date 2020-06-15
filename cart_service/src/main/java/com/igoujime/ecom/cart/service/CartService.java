package com.igoujime.ecom.cart.service;


import com.igoujime.ecom.cart.entity.Cart;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);

    Cart findOne(Integer id);

    List<Cart> findAll();

    void delete(Integer id);

    default void text(){

    }

}
