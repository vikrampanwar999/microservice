package com.igoujime.ecom.cart.service.impl;

import com.igoujime.ecom.cart.entity.Cart;
import com.igoujime.ecom.cart.kafka.KafkaCartService;
import com.igoujime.ecom.cart.mapper.JsonMapperWrapper;
import com.igoujime.ecom.cart.repository.CartRepository;
import com.igoujime.ecom.cart.service.CartService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartServiceImpl.class);

    private final CartRepository cartRepository;




    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart save(Cart cart) {
        LOGGER.info("Save cart: {}", cart);
        Cart saved = cartRepository.save(cart);
        return saved;
    }

    @Override
    public Cart findOne(Integer id) {
        LOGGER.info("Find cart, id: {}", id);
        return cartRepository.findOne(id);
    }

    @Override
    public List<Cart> findAll() {
        LOGGER.info("Find all carts");
        Iterable<Cart> carts = cartRepository.findAll();
        return IteratorUtils.toList(carts.iterator());
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("Delete cart, id: {}", id);
        cartRepository.delete(id);
    }

}
