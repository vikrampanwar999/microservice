package com.igoujime.ecom.checkout.service.impl;

import com.igoujime.ecom.checkout.entity.Checkout;
import com.igoujime.ecom.checkout.entity.CheckoutStatus;
import com.igoujime.ecom.checkout.kafka.KafkaCartService;
import com.igoujime.ecom.checkout.kafka.KeySet;
import com.igoujime.ecom.checkout.mapper.JsonMapperWrapper;
import com.igoujime.ecom.checkout.repository.CheckoutRepository;
import com.igoujime.ecom.checkout.service.CartService;
import com.igoujime.ecom.checkout.service.CheckoutService;
import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutServiceImpl.class);

    private final CheckoutRepository checkoutRepository;
    private final CartService cartService;
    private final KafkaCartService kafkaCartService;
    private final JsonMapperWrapper jsonMapper;

    @Value("${spring.kafka.producer.topic.cart}")
    private String cartTopic;

    @Autowired
    public CheckoutServiceImpl(CheckoutRepository checkoutRepository,
                               CartService cartService, KafkaCartService kafkaCartService, JsonMapperWrapper jsonMapper) {
        this.checkoutRepository = checkoutRepository;
        this.cartService = cartService;
        this.kafkaCartService = kafkaCartService;
        this.jsonMapper = jsonMapper;
    }

    @Override
    public Checkout save(Checkout checkout) {
        LOGGER.info("Save checkout: {}", checkout);
        checkout.setCart(cartService.save(checkout.getCart()));
        Checkout saved = checkoutRepository.save(checkout);
        if (saved.getCheckoutStatus().equals(CheckoutStatus.PAID)) {
            kafkaCartService.send(cartTopic, KeySet.UPDATE_CART, jsonMapper.writeValue(checkout.getCart()));
        }
        return saved;
    }

    @Override
    public Checkout findOne(Integer id) {
        LOGGER.info("Find checkout, id: {}", id);
        return checkoutRepository.findOne(id);
    }

    @Override
    public List<Checkout> findAll() {
        LOGGER.info("Find all checkouts");
        Iterable<Checkout> checkouts = checkoutRepository.findAll();
        return IteratorUtils.toList(checkouts.iterator());
    }

    @Override
    public void delete(Integer id) {
        LOGGER.info("Delete checkout, id: {}", id);
        Checkout checkout = checkoutRepository.findOne(id);
        if (checkout != null) {
            checkoutRepository.delete(checkout);
            cartService.delete(checkout.getCart().getId());
        }
    }

}
