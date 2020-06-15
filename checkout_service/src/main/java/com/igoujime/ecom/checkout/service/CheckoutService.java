package com.igoujime.ecom.checkout.service;


import com.igoujime.ecom.checkout.entity.Checkout;

import java.util.List;

public interface CheckoutService {

    Checkout save(Checkout checkout);

    Checkout findOne(Integer id);

    List<Checkout> findAll();

    void delete(Integer id);

}
