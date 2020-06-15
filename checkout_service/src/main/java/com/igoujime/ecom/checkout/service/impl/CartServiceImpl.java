package com.igoujime.ecom.checkout.service.impl;

import com.igoujime.ecom.checkout.entity.Cart;
import com.igoujime.ecom.checkout.kafka.KafkaCartService;
import com.igoujime.ecom.checkout.mapper.JsonMapperWrapper;
import com.igoujime.ecom.checkout.repository.CartRepository;
import com.igoujime.ecom.checkout.service.CartService;
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
    private final KafkaCartService kafkaCartService;
    private final JsonMapperWrapper jsonMapper;

    @Value("${spring.kafka.producer.topic.cart}")
    private String cartTopic;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, KafkaCartService kafkaCartService,
                           JsonMapperWrapper jsonMapper) {
        this.cartRepository = cartRepository;
        this.kafkaCartService = kafkaCartService;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Cart save(Cart cart) {
        LOGGER.info("Save cart: {}", cart);
        Cart saved = cartRepository.save(cart);
        //FIXME: send a cart updated
        // kafkaCartService.send(cartTopic, KeySet.UPDATE_CART, jsonMapper.writeValue(saved));
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
