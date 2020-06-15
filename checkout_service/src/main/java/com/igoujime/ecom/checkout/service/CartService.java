package com.igoujime.ecom.checkout.service;


import com.igoujime.ecom.checkout.entity.Cart;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);

    Cart findOne(Integer id);

    List<Cart> findAll();

    void delete(Integer id);

}
